package de.stecknitz.haushalt.entity.post

import java.util.UUID

data class Post(
    val id: UUID?,
    val titel: String,
    val beschreibung: String,
) {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(this.javaClass != other?.javaClass) return false
        other as Post
        return other.id == this.id

    }

    override fun hashCode() = id.hashCode()

}
