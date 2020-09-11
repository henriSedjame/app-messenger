package io.github.hsedjame.appmessenger.web

import io.github.hsedjame.appmessenger.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.renderAndAwait


@Component
class HtmlHandler(private val userRepository: UserRepository) {
    suspend fun index(serverRequest: ServerRequest) = ServerResponse
            .ok()
            .renderAndAwait("index", mapOf("users" to userRepository.findAll()))
}
