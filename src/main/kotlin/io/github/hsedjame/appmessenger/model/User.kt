package io.github.hsedjame.appmessenger.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
        @Id val id: Long?,
        val login: String,
        val firstname: String,
        val lastname: String
)
