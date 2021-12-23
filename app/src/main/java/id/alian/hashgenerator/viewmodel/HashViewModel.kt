package id.alian.hashgenerator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HashViewModel : ViewModel() {

    fun getHash(plainText: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String {
        Log.d("ViewModel", "toHex: ${byteArray.joinToString("") { "%02x".format(it) }}")
        return byteArray.joinToString("") { "%02x".format(it) }
    }
}