package com.mycorp.navigation

import org.koin.dsl.module

val navigationModule = module {
    single { Navigator() }
}