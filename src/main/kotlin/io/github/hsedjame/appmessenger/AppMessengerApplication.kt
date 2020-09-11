package io.github.hsedjame.appmessenger

import io.github.hsedjame.appmessenger.model.User
import io.github.hsedjame.appmessenger.repository.UserRepository
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait

@SpringBootApplication
class AppMessengerApplication {

    @Bean
    fun run(userRepository: UserRepository, operator: TransactionalOperator, client: DatabaseClient) = CommandLineRunner {

        args -> runBlocking {
            operator.executeAndAwait {

                client.execute("CREATE TABLE IF NOT EXISTS users( id BIGINT PRIMARY KEY GENERATED ALWAYS AS  IDENTITY NOT NULL , login VARCHAR , firstname VARCHAR , lastname VARCHAR)")
                        .fetch().awaitOneOrNull()

                userRepository.deleteAll()
                userRepository.save(User(id = null, login = "clenain", firstname = "chloe", lastname = "LE NAIN"))
                userRepository.save(User(id = null, login = "llenain", firstname = "lea", lastname = "LE NAIN"))
                userRepository.save(User(id = null, login = "hsedjame", firstname = "henri", lastname = "SEDJAME"))
                userRepository.save(User(id = null, login = "asedjame", firstname = "audrey", lastname = "SEDJAME"))
                userRepository.save(User(id = null, login = "gsedjame", firstname = "gaelle", lastname = "SEDJAME"))
                userRepository.save(User(id = null, login = "msedjame", firstname = "manuella", lastname = "SEDJAME"))
            }

        }
    }
}



fun main(args: Array<String>) {
    runApplication<AppMessengerApplication>(*args)
}
