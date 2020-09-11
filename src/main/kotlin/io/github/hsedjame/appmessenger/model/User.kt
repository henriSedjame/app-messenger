package io.github.hsedjame.appmessenger.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
        @Id val id: Long?,
        val login: String,
        val firstname: String,
        val lastname: String


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (login != other.login) return false
        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false

        return true
    }

    override fun hashCode(): Int {
        var result = login.hashCode()
        result = 31 * result + firstname.hashCode()
        result = 31 * result + lastname.hashCode()
        return result
    }
}
