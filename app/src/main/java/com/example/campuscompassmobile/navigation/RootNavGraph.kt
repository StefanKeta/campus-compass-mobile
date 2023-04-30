package com.example.campuscompassmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.AUTH ) {
        authNavGraph(navController = navController)
//        studentNavGraph(navController = navController)
//        landlordNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val STUDENT = "student_graph"
    const val LANDLORD = "landlord_graph"
}
