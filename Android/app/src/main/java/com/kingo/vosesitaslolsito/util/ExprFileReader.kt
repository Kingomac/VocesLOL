package com.kingo.vosesitaslolsito.util

import java.io.InputStream

class ExprFileReader(private val file: InputStream) {

    private fun processLine(line: String): Pair<String, String> {
        var contrabarra = false
        val expr = StringBuilder()
        val value = StringBuilder()
        var indicePalabra = 0
        var comillasAbiertas = false
        for (caracter in line) {

            if (caracter == '"' && !contrabarra) {
                comillasAbiertas = !comillasAbiertas
                continue
            }
            if (caracter == ',' && !comillasAbiertas) {
                indicePalabra++
                continue
            }
            if (caracter == '\\') {
                contrabarra = true
                continue
            }
            if (indicePalabra == 0) {
                expr.append(caracter)
            } else {
                value.append(caracter)
            }
        }
        return Pair(expr.toString(), value.toString())
    }

    fun readExpr() = sequence {
        val reader = file.bufferedReader(Charsets.UTF_8)
        var line = reader.readLine()
        while (line != null) {
            yield(processLine(line))
            line = reader.readLine()
        }
    }
    fun readAll(): Map<String, String> {
        val toret = mutableMapOf<String, String>()
        file.reader().forEachLine {
            val a = processLine(it)
            toret[a.first] = a.second
        }
        return toret.toMap()
    }

    companion object {
        fun readAll(stream: InputStream): Map<String, String> {
            return ExprFileReader(stream).readAll()
        }
    }
}