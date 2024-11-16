package com.example.loginsignupjetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loginsignupjetpack.R
import com.example.loginsignupjetpack.components.CustomGradientButton
import com.example.loginsignupjetpack.components.FloatingActionButtonExample
import com.example.loginsignupjetpack.components.MyTextFieldComponent
import com.example.loginsignupjetpack.components.NormalTextComponent
import com.example.loginsignupjetpack.components.TextWithDivider
import com.example.loginsignupjetpack.data.LoginViewModel
import com.example.loginsignupjetpack.data.UIEvent
import com.example.loginsignupjetpack.navigationGraph.Screen
import com.example.loginsignupjetpack.ui.theme.AppMainColor
import com.example.loginsignupjetpack.ui.theme.BackgroundMainColor
import com.example.loginsignupjetpack.ui.theme.TextColorCream

@Composable
fun SignUpScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
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
            val (imgStar, imgLine, textFirst, textSecond, textFirstName, textFieldFirstName,
                textEmail, textFieldEmail, textPassword, textFieldPassword,
                textMaxCharacter, imgCorrect, imgSignUp, orAnotherSignUp,textLogin) = createRefs()

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
                value = stringResource(id = R.string.create_account),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(textFirst) {
                    top.linkTo(imgStar.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.welcome),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textSecond) {
                    top.linkTo(textFirst.bottom, margin = 10.dp)
                    start.linkTo(parent.start)

                }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.name),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textFirstName) {
                    top.linkTo(textSecond.bottom, margin = 22.dp)
                    start.linkTo(parent.start)

                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.constrainAs(textFieldFirstName) {
                    top.linkTo(textFirstName.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, drawable = R.drawable.ic_person,
                isNameField = true,
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.NameChanged(it))
                }, errorMessage = loginViewModel.registrationUIState.value.nameError
            )
            /*NormalTextComponent(
                value = stringResource(id = R.string.last_name),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textLastname) {
                    top.linkTo(textFieldFirstName.bottom, margin = 22.dp)
                    start.linkTo(parent.start)

                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.enter_last_name),
                modifier = Modifier.constrainAs(textFieldLastName) {
                    top.linkTo(textLastname.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, drawable = R.drawable.ic_person
            )*/

            NormalTextComponent(
                value = stringResource(id = R.string.email),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textEmail) {
                    top.linkTo(textFieldFirstName.bottom, margin = 22.dp)
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
                isEmailField = true,
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                }, errorMessage = loginViewModel.registrationUIState.value.emailError
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
                isPassword = true,
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                }, errorMessage = loginViewModel.registrationUIState.value.passwordError
            )

            /*Image(painter = painterResource(id = R.drawable.ic_failure),
                contentDescription = null,
                Modifier
                    .size(14.dp)
                    .constrainAs(imgCorrect) {
                        top.linkTo(textMaxCharacter.top)
                        bottom.linkTo(textMaxCharacter.bottom)
                    }
            )
            NormalTextComponent(
                value = stringResource(id = R.string.max_character),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(textMaxCharacter) {
                    top.linkTo(textFieldPassword.bottom, margin = 16.dp)
                    start.linkTo(imgCorrect.end, 5.dp)
                }
            )*/
            CustomGradientButton(
                stringResource(id = R.string.sign_up),
                Modifier.constrainAs(imgSignUp) {
                    top.linkTo(textFieldPassword.bottom, margin = 22.dp)
                    start.linkTo(parent.start)
                }, onClick = {
                    loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
                    navController.navigate(route = Screen.Main.route)
                },
                enabled = true
            )

            Column(modifier = Modifier.constrainAs(orAnotherSignUp) {
                top.linkTo(imgSignUp.bottom, 24.dp)
                start.linkTo(parent.start)
            }) {
                TextWithDivider(
                    text = stringResource(id = R.string.or_sign_up),
                    dividerColor = AppMainColor
                )
                SignUpOptionsButton()
            }
            val annotatedString = buildAnnotatedString {
                append(stringResource(id = R.string.have_an_Account))
                // Style only the "Log in" part
                withStyle(style = SpanStyle(color = AppMainColor, fontWeight = FontWeight.Bold)) {
                    append(" Log in")
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
                    navController.navigate(route = Screen.Login.route)
                }.constrainAs(textLogin){
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
            )
        }
    }
}

@Composable
fun SignUpOptionsButton() {
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
    }
}

@Preview
@Composable
fun DefaultPreviewSignUpScreen() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}