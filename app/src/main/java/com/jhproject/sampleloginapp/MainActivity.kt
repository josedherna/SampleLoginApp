package com.jhproject.sampleloginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.jhproject.sampleloginapp.ui.theme.SampleLoginAppTheme

class MainActivity : ComponentActivity() {
    val userMap = HashMap<String, User>()
    val userViewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleLoginAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login_screen", builder = {
                    composable("login_screen") {
                        LoginScreen(navController, userViewModel, userMap)
                    }
                    composable("registration_screen") {
                        RegistrationScreen(navController, userMap)
                    }
                    composable("landingpage_screen") {
                        LandingPage(navController, userViewModel)
                    }
                })
            }
        }
    }
}