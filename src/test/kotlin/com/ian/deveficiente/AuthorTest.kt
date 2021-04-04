package com.ian.deveficiente

import com.ian.deveficiente.config.AuthorClient
import com.ian.deveficiente.dto.AuthorDTO
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*
import javax.inject.Inject
import javax.validation.ConstraintViolationException

@MicronautTest
class AuthorTest {

    @Inject
    lateinit var authorClient: AuthorClient



    @Test
    fun `should return status code 200 if data correct data is passed`() {
        val newAuthor = AuthorDTO()
        newAuthor.name = "Ian Oliveira"
        newAuthor.email = "${UUID.randomUUID()}@gmail.com"
        newAuthor.desc = "desc"

        val result = authorClient.save(newAuthor)

        assertEquals( "created", result.body())
        assertEquals( HttpStatus.OK, result.status())
    }

    @Test
    fun `should throws if no email is passed`() {
        val newAuthor = AuthorDTO()
        newAuthor.name = "Ian Oliveira"
        newAuthor.desc = "desc"

        assertThrows<ConstraintViolationException> {
            authorClient.save(newAuthor)
        }

    }

    @Test
    fun `should throws if no name  is passed`() {
        val newAuthor = AuthorDTO()

        newAuthor.email = "ianprogrammer@gmail.com"
        newAuthor.desc = "desc"

        lateinit var result: HttpResponse<*>;


        assertThrows<ConstraintViolationException> {
            authorClient.save(newAuthor)
        }
    }

    @Test
    fun `should not add author with duplcated email`() {

        val newAuthor = AuthorDTO()
        newAuthor.name = "Ian Oliveira"
        newAuthor.email = "ianprogrammer@gmail.com"
        newAuthor.desc = "desc"

        val result = authorClient.save(newAuthor)

        assertEquals( "created", result.body())
        assertEquals( HttpStatus.OK, result.status())

        try {
            val resultError = authorClient.save(newAuthor)
        }catch (ex: HttpClientResponseException){
            assertEquals( "Email already exists", ex.message)
        }




    }

}