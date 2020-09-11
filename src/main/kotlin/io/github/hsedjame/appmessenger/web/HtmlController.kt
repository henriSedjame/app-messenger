package io.github.hsedjame.appmessenger.web

import io.github.hsedjame.appmessenger.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun index(model: Model) : String{
        model["users"] = userRepository.findAll()
        return "index"
    }
}
