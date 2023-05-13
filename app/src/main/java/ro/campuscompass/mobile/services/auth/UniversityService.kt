package ro.campuscompass.mobile.services.auth

import ro.campuscompass.mobile.models.ModelResult
import ro.campuscompass.mobile.models.University
import ro.campuscompass.mobile.repository.Repository
import ro.campuscompass.mobile.repository.entities.UniversityEntity

class UniversityService(
        private val repository: Repository,
) {
    suspend fun getUniversities(): ModelResult<List<University>> = repository.getCollection("universities", UniversityEntity::class.java)
            .map { universities ->
                universities.map { entity ->
                    University(entity.id, entity.name)
                }
            }
}
