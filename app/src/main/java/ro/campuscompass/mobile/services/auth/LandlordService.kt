package ro.campuscompass.mobile.services.auth

import ro.campuscompass.mobile.models.LandLordProperty
import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.repository.entities.AddLandLordPropertyEntity
import ro.campuscompass.mobile.repository.entities.LandLordPropertyEntity

private const val LANDLORD_PROPERTIES = "landlord_properties"

class LandlordService(
        private val repository: Repository,
) {
    suspend fun getProperties(): ModelResult<List<LandLordProperty>> = repository.getCollection(LANDLORD_PROPERTIES, LandLordPropertyEntity::class.java)
            .map {
                it.map { entity ->
                    LandLordProperty(entity.id, entity.name, entity.address, entity.description,
                            entity.price, entity.bedrooms, entity.universityId, entity.universityName, entity.image, entity.availableFrom)
                }
            }

    suspend fun addProperty(addLandLordPropertyEntity: AddLandLordPropertyEntity) =
            repository.addDocument(LANDLORD_PROPERTIES, addLandLordPropertyEntity)
}
