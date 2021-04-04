package com.ian.deveficiente.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = 0,
    @NotNull
    @Column(name = "name", nullable = false)
    val name: String,
    @NotNull

    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Size(max=400)
    @Column(name = "desc", nullable = false)
    val desc: String,
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    val createdDate: Date? = null
)
