package com.devhyeon.youtubelayout.di

import com.devhyeon.youtubelayout.viewmodels.BottomNavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { BottomNavigationViewModel() }
}