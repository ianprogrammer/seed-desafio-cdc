package com.ian.deveficiente.service.impl

import com.ian.deveficiente.domain.Author
import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.dto.toAuthorDomain
import com.ian.deveficiente.service.contract.AuthorRepository
import io.micronaut.context.annotation.Primary

import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Primary
@Singleton
open class AuthorRepositoryImpl(
    @Inject
    private val entityManager: EntityManager
): AuthorRepository {

    @Transactional
    override fun save(data: AuthorDTO): Author {
        val author = data.toAuthorDomain();
        entityManager.persist(author)
        return author
    }
}