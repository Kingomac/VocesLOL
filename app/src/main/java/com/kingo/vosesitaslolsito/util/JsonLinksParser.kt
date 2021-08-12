package com.kingo.vosesitaslolsito.util

class JsonLinksParser {
    companion object {
        fun parseToMap(inputString: String): Map<String, Array<String>> {
            val lex = lexer(inputString)
            lex.forEach {
                print("'$it',")
            }
            print("\n")

            val keys = ArrayList<String>()
            val values = ArrayList<ArrayList<String>>()
            var i = 1
            while (i < lex.size) {
                if (lex[i] == "{" || lex[i] == "}" || lex[i] == ":") {
                    i++
                    continue
                }
                if (lex[i] == "[") {
                    val arr = ArrayList<String>()
                    i++
                    while (lex[i] != "]") {
                        arr.add(lex[i])
                        i++
                    }
                    i++
                    values.add(arr)
                } else {
                    keys.add(lex[i])
                    i++
                }
            }
            return keys.zip(values.map { it.toTypedArray() }).toMap()
        }

        fun parseToArray(inputString: String): Array<SoundsData> {
            val lex = lexer(inputString)
            lex.forEach {
                print("'$it',")
            }
            print("\n")

            val keys = ArrayList<String>()
            val values = ArrayList<ArrayList<String>>()
            var i = 1
            while (i < lex.size) {
                if (lex[i] == "{" || lex[i] == "}" || lex[i] == ":") {
                    i++
                    continue
                }
                if (lex[i] == "[") {
                    val arr = ArrayList<String>()
                    i++
                    while (lex[i] != "]") {
                        arr.add(lex[i])
                        i++
                    }
                    i++
                    values.add(arr)
                } else {
                    keys.add(lex[i])
                    i++
                }
            }
            println("keys (${keys.size}): ")
            println(keys)
            return Array(keys.size) { j ->
                    println("j: $j")
                    SoundsData(keys[j], values[j].toTypedArray())
            }
        }

        private fun lexer(str: String): ArrayList<String> {
            val arr = ArrayList<String>((str.length * 0.75).toInt())
            var i = 0
            while (i < str.length) {

                if (str[i] == '{' || str[i] == '}' || str[i] == ':' || str[i] == '[' || str[i] == ']') {
                    arr.add(str[i].toString())
                } else {
                    val sb = StringBuilder()
                    while (i < str.length && str[i] != '"') {
                        sb.append(str[i])
                        i++
                    }
                    arr.add(sb.toString())
                }
                i++
                while (i < str.length && (str[i] == '\n' || str[i] == '\r' || str[i] == ' ' || str[i] == ',')) {
                    i++
                }
            }
            val nulls = ArrayList<Int>()
            for (j in 0 until arr.size) {
                if (arr[j] == "") {
                    nulls.add(j - nulls.size)
                }
            }
            nulls.forEach {
                arr.removeAt(it)
            }
            return arr
        }
    }

}