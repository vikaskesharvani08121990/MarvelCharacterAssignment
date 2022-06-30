package com.example.common.utils

import java.math.BigInteger
import java.security.MessageDigest

class MD5HashKey {
    fun getHash(
        publicKey: String,
        privateKey: String,
        time: Long
    ): String {
        val request = "${time}${privateKey}${publicKey}"
        return getMd5(request)
    }

    private fun getMd5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}