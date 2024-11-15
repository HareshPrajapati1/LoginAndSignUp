package com.example.loginsignupjetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.loginsignupjetpack.R
import com.example.loginsignupjetpack.components.CustomGradientButton
import com.example.loginsignupjetpack.components.FloatingActionButtonExample
import com.example.loginsignupjetpack.components.MyTextFieldComponent
import com.example.loginsignupjetpack.components.NormalTextComponent
import com.example.loginsignupjetpack.components.TextWithDivider
import com.example.loginsignupjetpack.navigationGraph.Screen
import com.example.loginsignupjetpack.ui.theme.AppMainColor
import com.example.loginsignupjetpack.ui.theme.BackgroundMainColor
import com.example.loginsignupjetpack.ui.theme.TextColorCream

@Composable
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMainColor)
            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundMainColor)
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp)
        ) {
            val (imgStar, imgLine, textFirst, textSecond,
                textEmail, textFieldEmail, textPassword, textFieldPassword,
                textForget, imgSignUp, orAnotherSignUp) = createRefs()

            Image(
                painter = painterResource(R.drawable.ic_start), contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .constrainAs(imgStar) {
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start)
                    }
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth() // To make the line span the full width
                    .height(1.dp)
                    .constrainAs(imgLine) {
                        top.linkTo(imgStar.top)
                        bottom.linkTo(imgStar.bottom)
                        start.linkTo(imgStar.end, margin = 50.dp)
                    },
                color = AppMainColor
            )
            NormalTextComponent(
                value = stringResource(id = R.string.login_to_account),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(textFirst) {
                    top.linkTo(imgStar.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.welcome_back),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textSecond) {
                    top.linkTo(textFirst.bottom, margin = 10.dp)
                    start.linkTo(parent.start)

                }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.email),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textEmail) {
                    top.linkTo(textSecond.bottom, margin = 22.dp)
                    start.linkTo(parent.start)
                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_email),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.constrainAs(textFieldEmail) {
                    top.linkTo(textEmail.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, drawable = R.drawable.ic_email,
                isEmailField = true
            )
            NormalTextComponent(
                value = stringResource(id = R.string.password),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textPassword) {
                    top.linkTo(textFieldEmail.bottom, margin = 22.dp)
                    start.linkTo(parent.start)
                }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.password),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textPassword) {
                    top.linkTo(textFieldEmail.bottom, margin = 22.dp)
                    start.linkTo(parent.start)
                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_your_password),
                KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                modifier = Modifier.constrainAs(textFieldPassword) {
                    top.linkTo(textPassword.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, drawable = R.drawable.icons_password,
                isPassword = true
            )
            NormalTextComponent(
                value = stringResource(id = R.string.forgot_password),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textForget) {
                    top.linkTo(textFieldPassword.bottom, margin = 16.dp)
                    end.linkTo(parent.end)
                },
            )
            CustomGradientButton(
                stringResource(id = R.string.login),
                Modifier.constrainAs(imgSignUp) {
                    top.linkTo(textFieldPassword.bottom, margin = 64.dp)
                    start.linkTo(parent.start)
                }, onClick = {
                    navController.navigate(route = Screen.Main.route)
                }
            )

            Column(modifier = Modifier.constrainAs(orAnotherSignUp) {
                top.linkTo(imgSignUp.bottom, 24.dp)
                start.linkTo(parent.start)
            }) {
                TextWithDivider(
                    text = stringResource(id = R.string.or_login),
                    dividerColor = AppMainColor
                )
                LoginsOptionsButton(navController)
            }
        }
    }
}

@Composable
fun LoginsOptionsButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()  // Ensure the Column takes up the full screen height
            .padding(16.dp),  // Optional padding for the Column
        horizontalAlignment = Alignment.CenterHorizontally,  // Center content horizontally
        verticalArrangement = Arrangement.Center  // Center content vertically (optional)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center  // Center the buttons horizontally
        ) {
            FloatingActionButtonExample(
                iconResId = R.drawable.ic_google,  // Pass the desired icon resource
                onClick = {
                    // Handle button click
                }
            )

            FloatingActionButtonExample(
                iconResId = R.drawable.ic_facebook,
                onClick = {
                    // Handle button click
                }
            )
            FloatingActionButtonExample(
                iconResId = R.drawable.ic_github,
                onClick = {
                    // Handle button click
                }
            )
        }
        Spacer(modifier = Modifier.height(155.dp))
        val annotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.not_an_Account))
            withStyle(style = SpanStyle(color = AppMainColor, fontWeight = FontWeight.Bold)) {
                append(" Sign up")
            }
        }

        // Display the styled text using BasicText
        BasicText(
            text = annotatedString,  // Pass the AnnotatedString directly
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = TextColorCream
            ),
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.SignUp.route)
            }
        )
    }
}

@Preview
@Composable
fun DefaultPreviewSignUpScreen1() {
//    LoginScreen()
}