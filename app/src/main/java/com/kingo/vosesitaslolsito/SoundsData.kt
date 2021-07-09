package com.kingo.vosesitaslolsito

data class SoundsData(val key: String, val links: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if(other!!::class.java != SoundsData::class.java) {
            return false
        }
        val otherSound = other as SoundsData
        if(otherSound.links.size != this.links.size || this.key != otherSound.key) return false
        links.forEachIndexed{ i, s ->
            if(otherSound.links[i] != s) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + links.contentHashCode()
        return result
    }
}
