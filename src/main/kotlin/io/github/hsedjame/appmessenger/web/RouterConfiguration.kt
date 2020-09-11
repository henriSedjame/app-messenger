package io.github.hsedjame.appmessenger.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
class RouterConfiguration {

    @Bean
    fun routes(userHandler: UserHandler, htmlHandler: HtmlHandler): RouterFunction<ServerResponse> = coRouter {
        GET("/", htmlHandler::index)
        "/users".nest {
            POST("/", userHandler::create)
            GET("/", userHandler::findAll)
            GET("/{login}", userHandler::findByLogin)
            DELETE("/{login}", userHandler::deleteByLogin)
        }
    }

}
