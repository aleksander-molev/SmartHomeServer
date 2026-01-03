package com.example.smarthome.domain

import jakarta.validation.constraints.NotBlank

/**
 * Request payload for controlling a lamp.
 */
data class LampCommandRequest(
    @field:NotBlank(message = "Command is required")
    val command: String
)
