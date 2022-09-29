package id.co.astra.adel.metamor.base

import android.app.Application
import com.mazenrashed.printooth.Printooth
import id.co.astra.adel.metamor.di.databaseModule
import id.co.astra.adel.metamor.di.repository
import id.co.astra.adel.metamor.di.useCaseModule
import id.co.astra.adel.metamor.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Printooth.init(this)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    repository,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}