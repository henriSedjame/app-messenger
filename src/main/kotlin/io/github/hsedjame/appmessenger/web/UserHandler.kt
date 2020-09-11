package io.github.hsedjame.appmessenger.web

import io.github.hsedjame.appmessenger.model.User
import io.github.hsedjame.appmessenger.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.lang.Exception


@Component
class UserHandler(private val userRepository: UserRepository) {

    suspend fun findAll(serverRequest: ServerRequest) = ok().bodyAndAwait(userRepository.findAll())

    suspend fun findByLogin(serverRequest: ServerRequest) = userRepository.findByLogin(serverRequest.pathVariable("login"))
            ?.let { ok().bodyValueAndAwait(it) } ?: notFound().buildAndAwait()

    suspend fun deleteByLogin(serverRequest: ServerRequest) = userRepository.findByLogin(serverRequest.pathVariable("login"))
            .let { ok().buildAndAwait() }

    suspend fun create(serverRequest: ServerRequest) = userRepository.save(serverRequest.awaitBody<User>())
            .let { status(HttpStatus.CREATED).buildAndAwait() }
}

