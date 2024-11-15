package com.example.loginsignupjetpack.navigationGraph

sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object Login: Screen("login_screen")
    object SignUp: Screen("sig_up_screen")
}