package id.alian.hashgenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HashViewModel : ViewModel() {

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var _success = MutableLiveData<String>()
    val success: LiveData<String> = _success

    fun hash(plainText: String) {
        if (plainText.isEmpty()) {
            _message.postValue("Cannot be empty")
        } else {
            _success.postValue("")
        }
    }

    fun getHash(plainText: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }
}