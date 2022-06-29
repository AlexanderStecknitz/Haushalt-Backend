package de.stecknitz.haushalt.post.rest

import de.stecknitz.haushalt.post.entity.Post
import de.stecknitz.haushalt.post.rest.PostGetController.Companion.API_PATH
import de.stecknitz.haushalt.post.service.PostReadService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_PATH)
class PostGetController(val readService: PostReadService) {

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    suspend fun findAll():  ResponseEntity<List<Post>>? {
        logger.debug("FindAll: ")
        val posts = readService.findAll()
        return ok(posts)
    }

    companion object {
        const val API_PATH = "/api"
        private val logger: Logger = LoggerFactory.getLogger(PostGetController::class.java)
    }
}
