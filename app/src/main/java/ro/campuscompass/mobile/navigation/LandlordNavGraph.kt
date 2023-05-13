package ro.campuscompass.mobile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ro.campuscompass.mobile.screens.landlord.LandlordMainPage
import ro.campuscompass.mobile.screens.landlord.addproperty.LandlordAddPropertyPage

fun NavGraphBuilder.landlordNavGraph(
        navController: NavController,
) {
    navigation(
            route = Graph.LANDLORD,
            startDestination = LandlordNavGraph.LandlordMainPage.route
    ) {
        composable(LandlordNavGraph.LandlordMainPage.route) {
            LandlordMainPage(
                    onAddPropertyClick = {
                        navController.navigate(LandlordNavGraph.AddProperty.route)
                    }
            )
        }
        composable(LandlordNavGraph.AddProperty.route) {
            LandlordAddPropertyPage(
                    onAddPropertyClick = {
                        navController.popBackStack()
                    }
            )
        }
    }
}

sealed class LandlordNavGraph(val route: String) {
    object LandlordMainPage : LandlordNavGraph("landlord_main_page")
    object AddProperty : LandlordNavGraph("landlord_add_property")
}
