package ro.campuscompass.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavigationGraph(
        navController: NavHostController,
) {
    NavHost(navController = navController, route = Graph.ROOT.route, startDestination = Graph.AUTH.route) {
        authNavGraph(navController = navController)
        studentNavGraph(navController = navController)
        landlordNavGraph(navController = navController)
    }
}

sealed class Graph(val route: String) {
    object ROOT : Graph("root_graph")
    object AUTH : Graph("auth_graph")
    object STUDENT : Graph("student_graph")
    object LANDLORD : Graph("landlord_graph")
}
