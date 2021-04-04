package com.ian.deveficiente.service.contract

import com.ian.deveficiente.domain.Author
import com.ian.deveficiente.dto.AuthorDTO


interface AuthorRepository {
   fun save(data: AuthorDTO): Author
}





