package ro.campuscompass.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ro.campuscompass.mobile.screens.student.StudentMainPage

fun NavGraphBuilder.studentNavGraph(
        navController: NavController,
) {
    navigation(
            route = Graph.STUDENT,
            startDestination = StudentNavGraph.StudentMainPage.route
    ) {
        composable(StudentNavGraph.StudentMainPage.route) {
            StudentMainPage(

            )
        }
    }
}

sealed class StudentNavGraph(val route: String) {
    object StudentMainPage : StudentNavGraph("student_main_page")
}
