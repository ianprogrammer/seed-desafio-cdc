package com.ian.deveficiente.dto

import com.ian.deveficiente.domain.Author
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Introspected
class AuthorDTO {
    @NotEmpty(message = "Name may not be empty")
     var name = ""

    @NotEmpty(message = "email may not be empty")
    @Email(message = "Email should be valid")
    var email= ""

    @Size(max=400,message = "desc should not have more than 400 chars")
    var desc= ""
}


fun AuthorDTO.toAuthorDomain(): Author = Author(name = this.name, email = this.email, desc = this.desc)

