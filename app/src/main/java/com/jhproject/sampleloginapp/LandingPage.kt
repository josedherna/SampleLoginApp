package com.jhproject.sampleloginapp

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LandingPage(navController: NavController, viewModel: UserViewModel) {
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
            Text(text = stringResource(R.string.welcome_greeting),
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
                        Text(text = stringResource(R.string.userinfo_prompt),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    //Inner box that contains user information
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp)
                        .padding(start = 60.dp,
                            end = 60.dp),
                        contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(text = "First name: ${viewModel.user?.firstName}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary)
                            Text(text = "Last name: ${viewModel.user?.lastName}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary)
                            Text(text = "Email: ${viewModel.user?.email}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary)
                            Text(text = "Password: ${viewModel.user?.password}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary)
                            Text(text = "Date of Birth: ${viewModel.user?.dob}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary)
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
                                navController.navigate("login_screen")
                            },
                                modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
                                shape = RoundedCornerShape(10.dp)) {
                                Text(text = stringResource(R.string.logout_prompt),
                                    fontSize = 18.sp)
                            }
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