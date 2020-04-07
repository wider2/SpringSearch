package ee.search.service

import ee.search.model.User
import ee.search.repository.UserRepository
import ee.search.repository.UserSearch
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository, private val userSearch: UserSearch) {

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun deleteById(id: Long): Unit {
        return userRepository.deleteById(id)
    }

    fun search(text: String): List<User> {
        return userSearch.searchUsers(text)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }
}