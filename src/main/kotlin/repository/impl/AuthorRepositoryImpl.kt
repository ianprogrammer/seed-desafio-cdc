package com.ian.deveficiente.repository.impl

import com.ian.deveficiente.domain.Author
import com.ian.deveficiente.dto.AuthorDTO
import com.ian.deveficiente.dto.toAuthorDomain
import com.ian.deveficiente.exception.DuplicatedEmailException
import com.ian.deveficiente.repository.contract.AuthorRepository
import com.ian.deveficiente.util.Either
import io.micronaut.context.annotation.Primary

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Primary
@Singleton
open class AuthorRepositoryImpl(
    @PersistenceContext
    private val entityManager: EntityManager
) : AuthorRepository {

    @Transactional
    override fun save(data: AuthorDTO): Either<Author, Exception> {

        runCatching {
            val author = data.toAuthorDomain();

            entityManager.persist(author)
            entityManager.flush()
            return Either.Left(author)
        }.onFailure {
            if (it.message!!.contains("ConstraintViolationException")){
                return Either.Right(DuplicatedEmailException())
            }

            return Either.Right(Exception(it.message))


        }.getOrThrow()


    }
}