package com.example.loginsignupjetpack.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsignupjetpack.R
import com.example.loginsignupjetpack.data.rules.Validator
import com.example.loginsignupjetpack.ui.theme.AppMainColor
import com.example.loginsignupjetpack.ui.theme.BackgroundMainColor
import com.example.loginsignupjetpack.ui.theme.BorderColor
import com.example.loginsignupjetpack.ui.theme.GrayColor
import com.example.loginsignupjetpack.ui.theme.TextColorCream
import com.example.loginsignupjetpack.ui.theme.TextColorError
import com.example.loginsignupjetpack.ui.theme.TextColorGray
import com.example.loginsignupjetpack.ui.theme.WhiteColor


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
    isPassword: Boolean = false,
    isEmailField: Boolean = false,
    isNameField: Boolean = false,
    onTextSelected: (String) -> Unit,
    errorMessage: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var validationResult by remember { mutableStateOf(true) }

    val maxCharacter = 35
    val maxPassword = 12

    // Validation function using Validator class
    fun validateInput(input: String): Boolean {
        return when {
            isEmailField -> Validator.validateEmail(input).status
            isNameField -> Validator.validateName(input).status
            isPassword -> Validator.validatePassword(input).status
            else -> true
        }
    }

    val iconColor = if (isFocused) AppMainColor else WhiteColor

    val visualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            placeholder = { Text(text = labelValue, color = TextColorGray) },
            value = textValue.value,
            onValueChange = {
                validationResult = validateInput(it)
                onTextSelected(it)
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
                } else if (!validationResult && textValue.value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.ic_failure),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            },
        )

        // Show error messages if the input is invalid
        if (!validationResult && textValue.value.isNotEmpty()) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically // Aligns icon and text vertically
            ) {
                if (isPassword) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_failure),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(end = 5.dp) // Space between the icon and the text
                    )
                }
                Text(
                    text = when {
                        isEmailField -> stringResource(id = R.string.invalid_email)
                        isNameField -> stringResource(id = R.string.invalid_name)
                        isPassword -> stringResource(id = R.string.max_character)
                        else -> ""
                    },
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
    onClick: () -> Unit,
    enabled: Boolean = false
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
            .let {
                if (enabled) {
                    it.clickable(onClick = onClick) // Add clickable only when enabled
                } else {
                    it // Do not add clickable modifier when disabled
                }
            },


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
        isPassword = true,
        onTextSelected = {

        }
    )
}
