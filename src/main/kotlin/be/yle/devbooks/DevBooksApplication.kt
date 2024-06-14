package be.yle.devbooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevBooksApplication

fun main(args: Array<String>) {
	runApplication<DevBooksApplication>(*args)
}
