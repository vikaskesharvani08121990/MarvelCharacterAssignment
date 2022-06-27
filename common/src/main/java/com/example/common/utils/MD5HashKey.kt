package com.example.common.utils

import java.math.BigInteger
import java.security.MessageDigest
class  MD5HashKey {
    fun getHash(publicKey: String,
                privateKey: String,
                time: Long):String{
        var request="${time}${privateKey}${publicKey}"
        return md5(request)

    }
    fun md5(input:String):String{
        val md=MessageDigest.getInstance("MD5")
        return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }
}