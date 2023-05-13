package ro.campuscompass.mobile.repository.entities

data class LandLordPropertyEntity(
        val id: String,
        val name: String,
        val address: String,
        val description: String,
        val price: Int,
        val bedrooms: Int,
        val universityId: String,
        val universityName: String,
        val image: String?,
        val availableFrom: String?,
) {
    constructor() : this("", "", "", "", 0, 0, "", "", null, null)
}
