package com.hshim.gambling

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GamblingApplication

fun main(args: Array<String>) {
	runApplication<GamblingApplication>(*args)
}
