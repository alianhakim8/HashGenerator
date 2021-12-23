package id.alian.hashgenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HashViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HashViewModel::class.java)) {
            HashViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}