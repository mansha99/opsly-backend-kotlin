package com.opsly.backend.webfluxkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxkotlinApplication

fun main(args: Array<String>) {
	runApplication<WebfluxkotlinApplication>(*args)
	println(" **** Hello World *****")
}
