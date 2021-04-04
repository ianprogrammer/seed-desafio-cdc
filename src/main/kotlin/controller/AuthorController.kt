package com.ian.deveficiente.controller

import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.repository.contract.AuthorRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import javax.inject.Inject
import javax.validation.Valid
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated


@Controller("/\${api.version:v1}/author")
@Validated
class AuthorController(
    @Inject
    private val authorRepository: AuthorRepository
) {

    @Post
    fun save(@Body @Valid data: AuthorDTO): HttpResponse<String> {

        val result = authorRepository.save(data)
        return result.fold({
            HttpResponse.ok("created")
        }, {

            HttpResponse.status<String>(HttpStatus.BAD_REQUEST, it.message)
        }) as HttpResponse<String>

    }
}