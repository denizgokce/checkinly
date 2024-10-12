package com.checkinly.userservice.service

import com.checkinly.userservice.model.User
import com.checkinly.userservice.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): Optional<User> = userRepository.findById(id)

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, updatedUser: User): Optional<User> {
        return userRepository.findById(id).map { existingUser ->
            val userToUpdate = existingUser.copy(
                name = updatedUser.name,
                email = updatedUser.email
            )
            userRepository.save(userToUpdate)
        }
    }

    fun deleteUser(id: Long): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with email: $username")
        return org.springframework.security.core.userdetails.User(user.email, user.password, ArrayList())
    }
}