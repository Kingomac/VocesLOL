package com.kingo.vosesitaslolsito.util

import android.content.Context
import android.content.res.Resources

class Champ {
    companion object {
        fun get(context: Context): Array<String> {
            var xd: String?
            context.resources.openRawResource(context.resources.getIdentifier("champ_list.txt", "raw", context.packageName)).apply {
                xd = this.readBytes().toString(Charsets.UTF_8)
            }.close()
            if (xd == null) {
                return arrayOf();
            }
            return xd!!.split("\n").toTypedArray()
        }
    }
}