package com.example.campuscompassmobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.campuscompassmobile.MainPage
import com.example.campuscompassmobile.screens.auth.LandlordLogin
import com.example.campuscompassmobile.screens.auth.LandlordRegister
import com.example.campuscompassmobile.screens.auth.StudentLogin

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.MainLogin.route
    ) {
        composable(AuthScreen.MainLogin.route) {
            MainPage(
                studentLoginClick = {
                    navController.navigate(AuthScreen.StudentLogin.route)
                },
                landlordLoginClick = {
                    navController.navigate(AuthScreen.LandlordLogin.route)
                }
            )
        }
        composable(AuthScreen.StudentLogin.route) {
            StudentLogin(onLoginClick = {
                navController.popBackStack()
                navController.navigate(Graph.STUDENT)
            })
        }
        composable(AuthScreen.LandlordLogin.route) {
            LandlordLogin(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.LANDLORD)
                }, onDontHaveAccountClick = {
                    navController.navigate(AuthScreen.LandlordRegister.route)
                }
            )
        }
        composable(AuthScreen.LandlordRegister.route) {
            LandlordRegister(
                onRegisterClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.LANDLORD)
                },
                onAlreadyRegisteredClick = {
                    navController.navigate(AuthScreen.LandlordLogin.route)
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object MainLogin : AuthScreen("MAIN_LOGIN")
    object StudentLogin : AuthScreen("STUDENT_LOGIN")
    object LandlordRegister : AuthScreen("LANDLORD_REGISTER")
    object LandlordLogin : AuthScreen("LANDLORD_LOGIN")
}