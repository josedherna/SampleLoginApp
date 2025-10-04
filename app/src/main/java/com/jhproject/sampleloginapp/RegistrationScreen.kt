package com.jhproject.sampleloginapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

@Preview
@Composable
fun RegistrationScreen() {
    var passwordInput by rememberSaveable { mutableStateOf("") }
    var firstNameInput by rememberSaveable { mutableStateOf("") }
    var lastNameInput by rememberSaveable { mutableStateOf("") }
    var emailInput by rememberSaveable { mutableStateOf("") }
    var dobInput by rememberSaveable { mutableStateOf("") }

    var firstNameValidation by rememberSaveable { mutableStateOf<String?>(null) }
    var lastNameValidation by rememberSaveable { mutableStateOf<String?>(null) }
    var emailValidation by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordValidation by rememberSaveable { mutableStateOf<String?>(null) }
    var dobValidation by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Top Box that contains the text that says "Let's get to know you"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .padding(top = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.registration_greeting),
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 52.sp
            )
        }
        //Box that adds 20.dp of start and end padding to its children
        Box(
            modifier = Modifier
                .sizeIn(
                    minHeight = 400.dp,
                    minWidth = 600.dp,
                    maxHeight = 1000.dp,
                    maxWidth = 600.dp
                )
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            //Box that contains the Login text box and the box that contains the registration fields
            Box(
                modifier = Modifier
                    .sizeIn(
                        minHeight = 400.dp,
                        minWidth = 600.dp,
                        maxHeight = 800.dp,
                        maxWidth = 600.dp
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.TopCenter
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.createacc_prompt),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    //Inner box that contains the text fields
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp)
                            .padding(
                                start = 60.dp,
                                end = 60.dp,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            NameField(stringResource(R.string.firstname_prompt),
                                firstNameInput,
                                onValueChange = { firstNameInput = it
                                firstNameValidation = validateNameInput(it) },
                                error = firstNameValidation)
                            NameField(stringResource(R.string.lastname_prompt),
                                lastNameInput,
                                onValueChange = { lastNameInput = it
                                    lastNameValidation = validateNameInput(it) },
                                error = lastNameValidation)
                            EmailField(textInput = emailInput,
                                onValueChange = { emailInput = it
                                    emailValidation = validateEmail(it)},
                                error = emailValidation)
                            CreatePasswordField(textInput = passwordInput,
                                onValueChange = { passwordInput = it
                                passwordValidation = validatePasswordFormat(it)},
                                error = passwordValidation)
                            DOBField(textInput = dobInput,
                                onValueChange = { dobInput = it
                                dobValidation = validateDOB(it) },
                                error = dobValidation)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    //Second inner box that contains the login button and create account button
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .heightIn(min = 130.dp).padding(
                                start = 60.dp,
                                end = 60.dp
                            ),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                            Button(
                                onClick = {},
                                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.signup_prompt),
                                    fontSize = 18.sp
                                )
                            }
                            Text(
                                text = stringResource(R.string.have_account),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.clickable {
                                    //navController.navigate("registration_screen")
                                })
                        }
                    }
                }
            }
        }
        //Bottom box that adds space after the registration box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}

@Composable
fun NameField(labelText:String,
              textInput:String,
              onValueChange: (String) -> Unit,
              error: String?) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = {
            Text(labelText)
                },
        singleLine = true,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error,
                    color = MaterialTheme.colorScheme.error)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CreatePasswordField(textInput: String,
                        onValueChange: (String) -> Unit,
                        error: String?) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = {
            Text(text = stringResource(R.string.password_prompt))
                },
        singleLine = true,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error,
                    color = MaterialTheme.colorScheme.error)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DOBField(textInput:String,
             onValueChange: (String) -> Unit,
             error: String?) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = {
            Text(text = stringResource(R.string.dob_prompt))
        },
        singleLine = true,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error,
                    color = MaterialTheme.colorScheme.error)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )
}

fun validateNameInput(name:String): String? {
    return if (name.isEmpty()) {
        "Field cannot be empty"
    }
    else if (name.length < 3) {
        "Name must be 3 or more characters"
    }
    else if (name.length > 30) {
        "Name must not exceed 30 characters"
    }
    else {
        null
    }
}

fun validatePasswordFormat(password:String): String? {
    return if (password.isEmpty()) {
        "Field cannot be empty"
    }
    else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$", password)) {
        "Password must have at least one letter, one number and one special character"
    }
    else {
        null
    }
}

fun validateDOB(dob:String): String? {
    return if (dob.isEmpty()) {
        "Field cannot be empty"
    }
    else if (!Pattern.matches("(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d{2}", dob)) {
        "Date must be valid and in mm/dd/yyyy format"
    }
    else {
        null
    }
}