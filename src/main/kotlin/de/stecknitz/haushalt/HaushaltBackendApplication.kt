package de.stecknitz.haushalt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HaushaltBackendApplication

fun main(args: Array<String>) {
	runApplication<HaushaltBackendApplication>(*args)
}
