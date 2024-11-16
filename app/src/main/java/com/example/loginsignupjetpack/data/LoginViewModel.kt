package com.example.loginsignupjetpack.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginsignupjetpack.data.rules.Validator

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())


    fun onEvent(event: UIEvent) {
        validateDataWithRules()
        when (event) {
            is UIEvent.NameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()

        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val nameResult = Validator.validateName(registrationUIState.value.name)
        val emailResult = Validator.validateEmail(registrationUIState.value.email)
        val passwordResult = Validator.validatePassword(registrationUIState.value.password)

        registrationUIState.value = registrationUIState.value.copy(
            nameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}