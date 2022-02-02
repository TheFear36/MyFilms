package com.thefear.myfilms.model.repository.entitiesDTO

data class PageDTO(
    val docs: Array<MoveDTO>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PageDTO

        if (!docs.contentEquals(other.docs)) return false

        return true
    }

    override fun hashCode(): Int {
        return docs.contentHashCode()
    }
}
