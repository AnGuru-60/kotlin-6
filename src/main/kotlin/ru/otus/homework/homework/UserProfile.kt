@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.properties.Delegates.vetoable

/**
 * Профиль пользователя
 */
interface UserProfile {
    /**
     * Полное имя
     * Не должно принимать пустые строки
     */
    var fullName: String

    /**
     * Email.
     * Не должен принимать пустые и некорректные строки
     */
    var email: String

    /**
     * Профиль с логированием
     */
    interface Logging : UserProfile, WithLogging

    companion object {
        /**
         * Создает профиль пользователя
         */
        fun create(fullName: String, email: String): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return ProfileImplementationWithLogging(create(fullName, email))
        }
    }
}

/**
 * Проверка емейла на корректность
 */
private val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")

/**
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(fullName: String, email: String): UserProfile {

    override var fullName: String by NonEmptyStringDelegate(fullName)

    override var email: String by vetoable(email) { _, _, new -> new.isNotBlank() && emailRegex.matches(new)}
}

private class ProfileImplementationWithLogging(private val profile: UserProfile): UserProfile.Logging, UserProfile by profile {

    private val log: MutableList<String> = mutableListOf()

    override var fullName: String
        get() = profile.fullName
        set(value) {
            log.add("Changing `fullName` from '${profile.fullName}' to '$value'")
            profile.fullName = value
        }

    override var email: String
        get() = profile.email
        set(value) {
            log.add("Changing `email` from '${profile.email}' to '$value'")
            profile.email = value
        }

    override fun getLog(): List<String> = log
}