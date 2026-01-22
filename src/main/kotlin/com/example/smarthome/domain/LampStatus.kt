package com.example.smarthome.domain

enum class LampStatus {
    ON,
    OFF;

    companion object {
        fun from(command: String): LampStatus = entries.firstOrNull {
            it.name.equals(command.trim(), ignoreCase = true)
        } ?: throw InvalidLampCommandException("Unknown command: $command")
    }
}
