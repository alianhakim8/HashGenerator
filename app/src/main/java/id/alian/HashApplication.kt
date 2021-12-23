package id.alian

import android.app.Application
import id.alian.hashgenerator.viewmodel.HashViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class HashApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@HashApplication))
        bind() from singleton { HashViewModelFactory() }
    }
}