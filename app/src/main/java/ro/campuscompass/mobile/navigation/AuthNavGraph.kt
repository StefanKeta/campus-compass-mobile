package ro.campuscompass.mobile.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ro.campuscompass.mobile.MainPage
import ro.campuscompass.mobile.screens.auth.LandlordLogin
import ro.campuscompass.mobile.screens.auth.LandlordRegister
import ro.campuscompass.mobile.screens.auth.StudentLogin
import ro.campuscompass.mobile.services.auth.EmailAndPasswordClient
import ro.campuscompass.mobile.services.auth.SignInViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    emailAndPasswordClient: EmailAndPasswordClient
) {
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
                emailAndPasswordClient =     emailAndPasswordClient,
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.LANDLORD)
                }, onDontHaveAccountClick = {
                    navController.navigate(AuthScreen.LandlordRegister.route)
                }
            )
        }
        composable(AuthScreen.LandlordRegister.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LandlordRegister(
                state = state,
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
