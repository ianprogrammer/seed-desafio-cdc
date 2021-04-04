package com.ian.deveficiente.controller

import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.dto.toAuthorDomain
import com.ian.deveficiente.service.contract.AuthorRepository
import com.ian.deveficiente.service.impl.AuthorRepositoryImpl
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
    fun addController(@Body @Valid  data: AuthorDTO): HttpResponse<*> {
        return try {
            authorRepository.save(data)
            HttpResponse.ok("")
        } catch (ex: Exception) {
            HttpResponse.badRequest(ex.message)
        }
    }
}