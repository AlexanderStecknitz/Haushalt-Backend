package de.stecknitz.haushalt.post.service

import de.stecknitz.haushalt.post.entity.Post
import org.springframework.stereotype.Service

@Service
class PostReadService {

    suspend fun findAll(): List<Post> = listOf(
            Post(
                id = 1,
                beschreibung = "FindAll-Mock-1",
            ),
            Post(
                id = 2,
                beschreibung = "FindAll-Mock-2",
            ),
            Post(
                id = 3,
                beschreibung = "FindAll-Mock-3",
            ),
            Post(
                id = 4,
                beschreibung = "FindAll-Mock-4",
            ),
    )

}
