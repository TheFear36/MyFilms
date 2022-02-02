package com.thefear.myfilms.model.repository.entitiesDTO

data class MoveDTO(
    val poster: PosterDTO?,
    val rating: RatingDTO?,
    val type: String?,
    val name: String?,
    val description: String?,
    val year: Int?
)
