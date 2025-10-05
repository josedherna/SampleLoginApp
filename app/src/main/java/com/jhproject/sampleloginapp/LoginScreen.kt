package com.jhproject.sampleloginapp

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, viewModel: UserViewModel, userMap: HashMap<String, User>) {
    var emailInput by rememberSaveable { mutableStateOf("") }
    var passwordInput by rememberSaveable { mutableStateOf("") }

    var emailValidation by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordValidation by rememberSaveable { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Top Box that contains the text that says "Hello There"
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp)
            .padding(top = 50.dp),
            contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.landing_heading),
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 52.sp)
        }
        //Box that adds 20.dp of start and end padding to its children
        Box(modifier = Modifier
            .sizeIn(minHeight = 400.dp,
                minWidth = 600.dp,
                maxHeight = 580.dp,
                maxWidth = 600.dp)
            .padding(start = 20.dp,
                end = 20.dp)) {
            //Box that contains the Login text box and the box that contains the username and password fields
            Box(modifier = Modifier
                .sizeIn(minHeight = 400.dp,
                    minWidth = 600.dp,
                    maxHeight = 580.dp,
                    maxWidth = 600.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.TopCenter) {
                Column {
                    //Box that contains the subheader text
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp)
                        .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center) {
                        Text(text = stringResource(R.string.login_prompt),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    //Inner box that contains the username and password fields
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp)
                        .padding(start = 60.dp,
                            end = 60.dp),
                        contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            EmailField(textInput = emailInput,
                                onValueChange = { emailInput = it
                                    emailValidation = validateEmail(it)},
                                error = emailValidation)
                            PasswordField(textInput = passwordInput,
                                onValueChange = { passwordInput = it
                                passwordValidation = validatePassword(it)},
                                error = passwordValidation)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    //Second inner box that contains the login button and create account button
                    Box(modifier = Modifier.fillMaxWidth()
                        .heightIn(min = 130.dp).padding(start = 60.dp,
                            end = 60.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                            Button(onClick = {
                                if(emailInput.isEmpty() && passwordInput.isEmpty()) {
                                    Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                                }
                                else if (userMap.containsKey(emailInput)) {
                                    val user = userMap.get(emailInput)
                                    if (user?.password == passwordInput) {
                                        viewModel.user = user
                                        navController.navigate("landingpage_screen")
                                    }
                                    else {
                                        Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                                shape = RoundedCornerShape(10.dp)) {
                                Text(text = stringResource(R.string.login_prompt),
                                    fontSize = 18.sp)
                            }
                            Text(text = stringResource(R.string.createacc_prompt),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.clickable {
                                    navController.navigate("registration_screen")
                                })
                        }
                    }
                }
            }
        }
        //Bottom box that adds space after the login box
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp),
            contentAlignment = Alignment.Center) {
        }
    }
}

@Composable
fun EmailField(textInput:String,
               onValueChange: (String) -> Unit,
               error: String?) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.username_prompt)) },
        singleLine = true,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
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
fun PasswordField(textInput:String,
                  onValueChange: (String) -> Unit,
                  error: String?) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.password_prompt)) },
        singleLine = true,
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

fun validateEmail(email:String): String? {
    return if (email.isEmpty()) {
        "Field cannot be empty"
    }
    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        "Invalid email address format"
    }
    else {
        null
    }
}

fun validatePassword(password:String): String? {
    return if (password.isEmpty()) {
        "Field cannot be empty"
    }
    else {
        null
    }
}