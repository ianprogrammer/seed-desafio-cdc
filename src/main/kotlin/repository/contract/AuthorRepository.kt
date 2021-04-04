package com.ian.deveficiente.repository.contract

import com.ian.deveficiente.domain.Author
import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.util.Either


interface AuthorRepository {
   fun save(data: AuthorDTO): Either<Author, Exception>
}





