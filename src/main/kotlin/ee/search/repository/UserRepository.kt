package ee.search.repository

import ee.search.model.User
import org.springframework.data.jpa.repository.JpaRepository

//internal
interface UserRepository: JpaRepository<User, Long>