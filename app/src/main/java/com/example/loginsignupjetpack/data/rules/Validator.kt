package com.example.loginsignupjetpack.data.rules

object Validator {

    fun validateName(name: String): ValidationResult {

        val nameRegex = "^[A-Za-z ]{3,35}$"  // Name should be between 3 and 35 characters

        return ValidationResult(
            (name.matches(Regex(nameRegex)))
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return ValidationResult(
            (email.matches(Regex(emailRegex)))
        )
    }

    fun validatePassword(password: String): ValidationResult {
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$"
        return ValidationResult(
            (password.matches(Regex(passwordRegex)))
        )
    }

    data class ValidationResult(
        val status: Boolean = false
    )
}