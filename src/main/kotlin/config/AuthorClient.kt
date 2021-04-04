package com.ian.deveficiente.config

import com.ian.deveficiente.dto.AuthorDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.validation.Valid


@Client("/")
interface AuthorClient {

    @Post("/\${api.version:v1}/author")
    fun save(@Body @Valid data: AuthorDTO): HttpResponse<String>
}