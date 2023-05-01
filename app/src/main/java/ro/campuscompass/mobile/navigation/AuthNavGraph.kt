package ro.campuscompass.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ro.campuscompass.mobile.MainPage
import ro.campuscompass.mobile.screens.auth.LandlordLogin
import ro.campuscompass.mobile.screens.auth.LandlordRegister
import ro.campuscompass.mobile.screens.auth.StudentLogin

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthNavGraph.MainLogin.route
    ) {
        composable(AuthNavGraph.MainLogin.route) {
            MainPage(
                studentLoginClick = {
                    navController.navigate(AuthNavGraph.StudentLogin.route)
                },
                landlordLoginClick = {
                    navController.navigate(AuthNavGraph.LandlordLogin.route)
                }
            )
        }
        composable(AuthNavGraph.StudentLogin.route) {
            StudentLogin(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.STUDENT)
                })
        }
        composable(AuthNavGraph.LandlordLogin.route) {
            LandlordLogin(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.LANDLORD)
                },
                onDontHaveAccountClick = {
                    navController.navigate(AuthNavGraph.LandlordRegister.route)
                }
            )
        }
        composable(AuthNavGraph.LandlordRegister.route) {
            LandlordRegister(
                onRegisterClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.LANDLORD)
                },
                onAlreadyRegisteredClick = {
                    navController.navigate(AuthNavGraph.LandlordLogin.route)
                }
            )
        }
    }
}

sealed class AuthNavGraph(val route: String) {
    object MainLogin : AuthNavGraph("main_login")
    object StudentLogin : AuthNavGraph("student_login")
    object LandlordRegister : AuthNavGraph("landlord_register")
    object LandlordLogin : AuthNavGraph("landlord_login")
}
