package com.kingo.vosesitaslolsito.util

import android.content.Context
import android.content.res.AssetManager
import android.content.res.loader.AssetsProvider
import android.util.Log
import kotlinx.coroutines.flow.asFlow
import java.io.BufferedReader
import java.io.InputStream
import java.util.regex.Pattern
import kotlin.streams.asStream

object SoundNamer {

    var generalExpr: Map<String,String> = mapOf()
    var subExpr: Map<String, String> = mapOf()

    private fun getPairNameNumber(nombre: String): Pair<String, Int> {
        val match = Regex("[0-9]+$").find(nombre)
        match?.let {
            return Pair(nombre.substring(0, it.range.first), nombre.substring(it.range).toInt())
        }
        return Pair(nombre, 0)
    }

    fun rename(nombre: String): String {
        val pair = getPairNameNumber(nombre)
        val split = pair.first.split("(?=[A-Z0-9])".toRegex()).filter { it != "" }
        val n = pair.first.lowercase()

        listOf(subExpr, generalExpr).forEach { expr ->
            expr.forEach {
                Log.d("EXPRESSION", "${it.key} check $n")
                if (it.key.toRegex().matches(n)) {
                    var result: String = it.value
                    "\\$[0-9]+".toRegex().findAll(result).forEach { exprParam ->
                        val index = exprParam.value.replace("$", "").toInt()
                        result = result.replace(exprParam.value, if(index < 0 || index >= split.size) "" else split[index])
                    }
                    return "$result${if(pair.second > 0) " ${pair.second}" else ""}"
                }
            }
        }


/*
        listOf(subExpr, generalExpr).forEach { subexpr ->
            subexpr.forEach {
                Log.d("EXPRESSION", "${it.key} check $n")
                if (it.key.toRegex().matches(n)) {
                    return "${it.value} ${if (pair.second > 0) pair.second else ""}"
                }
            }
        }
*/
        return nombre
    }
}