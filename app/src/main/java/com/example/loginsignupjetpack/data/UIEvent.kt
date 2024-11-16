package com.example.loginsignupjetpack.data

sealed class UIEvent {

    data class NameChanged(val name: String) : UIEvent()
    data class EmailChanged(val email: String) : UIEvent()
    data class PasswordChanged(val password: String) : UIEvent()

    object RegisterButtonClicked:UIEvent()
}