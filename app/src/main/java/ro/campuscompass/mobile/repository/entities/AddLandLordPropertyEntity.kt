package ro.campuscompass.mobile.repository.entities

data class AddLandLordPropertyEntity(
        val name: String,
        val address: String,
        val description: String,
        val price: Int,
        val bedrooms: Int,
        val universityId: String,
        val universityName: String,
)
