package ee.search.controllers

import ee.search.model.User
import ee.search.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAll(): List<User> {
        return userService.findAll()
    }

    @GetMapping("/users/details")
    fun getById(@RequestParam(value = "id", defaultValue = "0") id: Long) : Optional<User> {
        return userService.findById(id)
    }

    @DeleteMapping("/users")
    fun deleteById(@RequestParam(value = "id", defaultValue = "0") id: Long) {
        return userService.deleteById(id)
    }

    @GetMapping("/users/search")
    fun searchBy(query: String): List<User> {
        return userService.search(query)
    }

    @PostMapping("/users")
    fun insertUser(@RequestBody user: User): User {
        return userService.saveUser(user)
    }
}