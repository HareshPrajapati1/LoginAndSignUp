package com.example.loginsignupjetpack.components

import android.R.color
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignupjetpack.R
import com.example.loginsignupjetpack.screens.SignUpScreen
import com.example.loginsignupjetpack.ui.theme.*


@Composable
fun NormalTextComponent(
    value: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        modifier = modifier
            .heightIn(min = 0.dp),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif
        ), color = colorResource(id = R.color.text_color_cream),
        textAlign = TextAlign.Start
    )
}


@Composable
fun MyTextFieldComponent(
    labelValue: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier,
    drawable: Int,
    isPassword: Boolean = false,  // New parameter to indicate if this is a password field
    isEmailField: Boolean = false,  // New parameter to determine if this is an email field
    isNameField: Boolean = false,   // New parameter to determine if this is a name field
) {
    val textValue = remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isValidEmail by remember { mutableStateOf(true) } // To track email validation state
    var isValidName by remember { mutableStateOf(true) }  // To track name validation state
    var isValidPassword by remember { mutableStateOf(true) } // To track password validation state

    val maxCharacter = 35
    val maxPassword = 12

    // Regex patterns for email, name, and password
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    val nameRegex = "^[A-Za-z ]{2,35}$"  // Name should be between 2 and 35 characters
    val passwordRegex =
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$"  // Password validation (at least 8 characters, with letters, digits, and special chars)

    // Change icon color based on focus state
    val iconColor = if (isFocused) AppMainColor else WhiteColor

    // Set visual transformation based on password visibility
    val visualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    // Validation logic for email, name, and password
    if (isEmailField) {
        isValidEmail = textValue.value.matches(Regex(emailRegex))
    }
    if (isNameField) {
        isValidName = textValue.value.matches(Regex(nameRegex))
    }
    if (isPassword) {
        isValidPassword = textValue.value.matches(Regex(passwordRegex))
    }

    // Using Column to wrap TextField and error message
    Column(
        modifier = modifier
            .fillMaxWidth()
        // You can adjust the padding here
    ) {
        OutlinedTextField(
            placeholder = { Text(text = labelValue, color = TextColorGray) },
            value = textValue.value,
            onValueChange = {
                // Handle validation based on field type
                if (isEmailField) {
                    isValidEmail = it.matches(Regex(emailRegex))
                }
                if (isNameField) {
                    isValidName = it.matches(Regex(nameRegex))
                }
                if (isPassword) {
                    isValidPassword = it.matches(Regex(passwordRegex))
                }

                // Update text value for name, password, or email
                if (it.length <= maxCharacter && !isPassword) {
                    textValue.value = it
                } else if (it.length <= maxPassword) {
                    textValue.value = it
                }
            },
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(40.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = TextStyle(
                fontSize = 18.sp,
                lineHeight = 24.sp
            ),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrayColor,
                unfocusedContainerColor = GrayColor,
                disabledContainerColor = GrayColor,
                focusedLabelColor = WhiteColor,
                focusedTextColor = TextColorCream,
                unfocusedTextColor = TextColorCream,
                focusedIndicatorColor = AppMainColor,
                cursorColor = AppMainColor,
                unfocusedIndicatorColor = BorderColor,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            visualTransformation = visualTransformation,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = iconColor
                )
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility) else painterResource(
                                id = R.drawable.ic_not_visible
                            ),
                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                            tint = iconColor
                        )
                    }
                } else {
                    if (isEmailField && !isValidEmail && textValue.value.isNotEmpty() ||
                        isNameField && !isValidName && textValue.value.isNotEmpty()
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.ic_failure),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            },
        )

        // Show error messages if the input is invalid
        if (isEmailField && !isValidEmail && textValue.value.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.invalid_email),
                color = TextColorError,
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif)
            )
        }
        if (isNameField && !isValidName && textValue.value.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.invalid_name),
                color = TextColorError,
                style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif)
            )
        }
        if (isPassword && !isValidPassword && textValue.value.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(), // This ensures the row takes up the full width
                verticalAlignment = Alignment.CenterVertically // Aligns both image and text vertically at the center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_failure),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 5.dp) // Space between the image and text
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.max_character),
                    color = TextColorError,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Serif)
                )
            }
        }

    }
}


@Composable
fun CustomGradientButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth() // Ensures the button takes full width
            .clip(RoundedCornerShape(10.dp)) // Rounded corners for the button
            .background( // Apply gradient background here
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFb64957), // Main color
                        Color(0xFFd1626c), // Lighter variant
                        Color(0xFF944046)  // Darker variant
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = onClick
            ),

        contentAlignment = Alignment.Center // Centers the content (Text) inside the Box
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(14.dp)
        )
    }
}

@Composable
fun FloatingActionButtonExample(
    iconResId: Int,
    onClick: () -> Unit
) {
    // Create an InteractionSource to detect press interactions
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()  // Track if the button is pressed

    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .border(
                1.dp,
                if (isPressed) AppMainColor else BorderColor,
                RoundedCornerShape(10.dp)
            )
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(color = Color.DarkGray)
            ),
        containerColor = BackgroundMainColor,
        interactionSource = interactionSource  // Pass interactionSource to detect press state
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = iconResId),
            contentDescription = "FAB Icon",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun TextWithDivider(text: String, dividerColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // First divider
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )

        // Text in the center
        Text(
            text = text,
            color = TextColorCream,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp
        )

        // Second divider
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )
    }
}



@Preview
@Composable
fun DefaultPreviewSignUpScreen() {
    MyTextFieldComponent(
        labelValue = stringResource(id = R.string.enter_your_password),
        KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        modifier = Modifier, drawable = R.drawable.icons_password,
        isPassword = true
    )
}
