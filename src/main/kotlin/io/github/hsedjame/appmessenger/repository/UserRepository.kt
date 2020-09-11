package io.github.hsedjame.appmessenger.repository

import io.github.hsedjame.appmessenger.model.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component

@Component
interface UserRepository: CoroutineCrudRepository<User, Long> {

    /* Find one user by its login */
    @Query("SELECT * FROM users WHERE login = ?1")
    suspend fun findByLogin(login: String) : User?

    @Query("DELETE FROM users WHERE login = :login")
    suspend fun deleteByLogin(login: String): Unit;

}
