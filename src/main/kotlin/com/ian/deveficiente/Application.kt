package com.ian.deveficiente

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.ian.deveficiente")
		.start()
}

