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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleLoginAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login_screen", builder = {
                    composable("login_screen") {
                        LoginScreen(navController)
                    }
                    composable("registration_screen") {
                        RegistrationScreen()
                    }
                })
            }
        }
    }
}