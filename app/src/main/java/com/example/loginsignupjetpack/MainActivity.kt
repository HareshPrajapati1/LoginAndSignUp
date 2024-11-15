package com.example.loginsignupjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginsignupjetpack.navigationGraph.NavigationStack
import com.example.loginsignupjetpack.screens.LoginScreen
import com.example.loginsignupjetpack.screens.SignUpScreen
import com.example.loginsignupjetpack.ui.theme.LoginSignUpJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationStack()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginSignUpJetpackTheme {
//        SignUpScreen()
    }
}