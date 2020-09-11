package io.github.hsedjame.appmessenger

import io.github.hsedjame.appmessenger.model.User
import io.github.hsedjame.appmessenger.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersIntegrationTests @Autowired constructor (
        val client: WebTestClient,
        val databaseClient: DatabaseClient,
        val userRepository: UserRepository) {

    val user1 = User(id = null, login = "clenain", firstname = "chloe", lastname = "LE NAIN")
    val user2 = User(id = null, login = "llenain", firstname = "lea", lastname = "LE NAIN")
    val user3 = User(id = null, login = "hsedjame", firstname = "henri", lastname = "SEDJAME")
    val user4 = User(id = null, login = "gsedjame", firstname = "gaelle", lastname = "SEDJAME")


    @BeforeEach
    fun setUp() {
        runBlocking {
           databaseClient.execute("CREATE TABLE IF NOT EXISTS users( id BIGINT PRIMARY KEY GENERATED ALWAYS AS  IDENTITY NOT NULL , login VARCHAR , firstname VARCHAR , lastname VARCHAR)")
                   .fetch().awaitOneOrNull()
            userRepository.deleteAll()
            userRepository.save(user1)
            userRepository.save(user2)
            userRepository.save(user3)
            userRepository.save(user4)
        }
    }

    @Test
    fun `Find all users`() {
        runBlocking {
            client.get()
                    .uri("/users/")
                    .exchange()
                    .expectBodyList(User::class.java)
                    .contains(user1, user2, user3, user4)
        }
    }
}
