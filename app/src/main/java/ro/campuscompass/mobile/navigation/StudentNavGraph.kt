package ro.campuscompass.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ro.campuscompass.mobile.screens.student.StudentApplicationPage
import ro.campuscompass.mobile.screens.student.StudentMainPage

const val UNIVERSITY_ID = "uniId"
const val STUDENT_ID = "studentId"
const val SELECTED_OFFER_ID = "selectedOfferId"

fun NavGraphBuilder.studentNavGraph(
        navController: NavController,
) {
    navigation(route = Graph.STUDENT.route, startDestination = StudentNavGraph.StudentMainPage.route) {
        composable(StudentNavGraph.StudentMainPage.route, arguments = listOf(navArgument(STUDENT_ID) { type = NavType.StringType }, navArgument(UNIVERSITY_ID) { type = NavType.StringType })) {
            val studentId = requireNotNull(it.arguments?.getString(STUDENT_ID))
            val uniId = requireNotNull(it.arguments?.getString(UNIVERSITY_ID))
            val selectedOfferId: (String) -> Unit = { offerId -> navController.popBackStack();navController.navigate(StudentNavGraph.StudentApplicationPage.withOfferId(offerId)) }
            StudentMainPage(studentId, uniId, selectedOfferId)
        }
        composable(route = StudentNavGraph.StudentApplicationPage.route) {
            val offerId = requireNotNull(it.arguments?.getString(SELECTED_OFFER_ID))
            StudentApplicationPage(offerId = offerId)
        }
    }
}

sealed class StudentNavGraph(val route: String) {
    object StudentMainPage : StudentNavGraph("student_main/{$STUDENT_ID}/{$UNIVERSITY_ID}") {
        fun withStudentIdAndUniId(studentId: String, uniId: String): String {
            return "student_main/$studentId/$uniId"
        }
    }

    object StudentApplicationPage : StudentNavGraph("student_application/{$SELECTED_OFFER_ID}") {
        fun withOfferId(offerId: String): String {
            return "student_application/$offerId"
        }
    }
}
