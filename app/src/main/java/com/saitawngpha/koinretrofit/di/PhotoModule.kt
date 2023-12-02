package com.saitawngpha.koinretrofit.di

import com.saitawngpha.koinretrofit.repository.ApiRepository
import com.saitawngpha.koinretrofit.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 02/12/2023.
 */

val photoModule = module {
    factory { ApiRepository(get()) }
    viewModel() { MainViewModel(get())}
}
