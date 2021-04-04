package com.ian.deveficiente.controller

import com.fasterxml.jackson.core.JsonParseException
import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.service.contract.AuthorRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import javax.inject.Inject
import javax.validation.Valid
import io.micronaut.http.HttpResponse;
import io.micronaut.validation.Validated


@Controller("/\${api.version:v1}/author")
@Validated
class AuthorController(
    @Inject
    private val authorRepository: AuthorRepository
) {

    @Post
    fun save(@Body @Valid data: AuthorDTO): HttpResponse<String> {
        return try {
            authorRepository.save(data)
            HttpResponse.ok("created")
        } catch (ex: Exception) {
            HttpResponse.badRequest(ex.message)
        }
    }
}