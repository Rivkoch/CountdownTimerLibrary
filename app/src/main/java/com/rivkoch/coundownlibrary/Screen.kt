package com.rivkoch.coundownlibrary

sealed class Screen(val route: String){
    object  CountdownScreen: Screen("countdown_screen")

}
