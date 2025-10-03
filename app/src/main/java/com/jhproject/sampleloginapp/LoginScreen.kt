package com.jhproject.sampleloginapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LoginScreen() {
    var usernameInput by rememberSaveable { mutableStateOf("") }
    var passwordInput by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Top Box that contains the text that says "Hello There"
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp),
            contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.welcome_greeting),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface)
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
                    //Inner box that contains the username and password fields
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp)
                        .padding(start = 60.dp,
                            end = 60.dp),
                        contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            UsernameField(textInput = usernameInput,
                                onValueChange = { usernameInput = it } )
                            PasswordField(textInput = passwordInput,
                                onValueChange = { passwordInput = it} )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()
                        .heightIn(min = 130.dp).padding(start = 60.dp,
                            end = 60.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                            Button(onClick = {},
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
                                    ;
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
fun UsernameField(textInput:String,
                  onValueChange: (String) -> Unit) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.username_prompt)) },
        singleLine = true,
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
                  onValueChange: (String) -> Unit) {
    OutlinedTextField(value = textInput,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.password_prompt)) },
        singleLine = true,
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