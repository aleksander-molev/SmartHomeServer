package com.example.smarthome

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmartHomeServerApplication

fun main(args: Array<String>) {
    runApplication<SmartHomeServerApplication>(*args)
}
