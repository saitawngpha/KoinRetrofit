package com.saitawngpha.koinretrofit

import android.app.Application
import com.saitawngpha.koinretrofit.di.apiModule
import com.saitawngpha.koinretrofit.di.photoModule
import org.koin.core.context.startKoin

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(apiModule, photoModule)
        }
    }
}