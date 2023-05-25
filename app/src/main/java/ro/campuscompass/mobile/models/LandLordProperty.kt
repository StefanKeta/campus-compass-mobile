package ro.campuscompass.mobile.models

data class LandLordProperty(
        val id: String,
        val name: String,
        val address: String,
        val description: String,
        val price: Int,
        val bedrooms: Int,
        val universityId: String,
        val universityName: String,
        val isTakenBy: String,
        val image: String?,
        val availableFrom: String?,
)
