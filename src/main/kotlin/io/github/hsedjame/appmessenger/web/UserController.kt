package io.github.hsedjame.appmessenger.web

import io.github.hsedjame.appmessenger.model.User
import io.github.hsedjame.appmessenger.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun findAll() = userRepository.findAll()

    @GetMapping("/{login}")
    suspend fun findByLogin(@PathVariable("login") login: String) = userRepository.findByLogin(login) ?: throw Exception("No user found with login $login");

    @DeleteMapping("/{login}")
    suspend fun deleteByLogin(@PathVariable("login") login: String) = userRepository.deleteByLogin(login)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody user: User) = userRepository.save(user)
}
