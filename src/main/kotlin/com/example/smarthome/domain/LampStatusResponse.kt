package com.example.smarthome.domain

/**
 * Simple DTO returned by REST endpoints with lamp details.
 */
data class LampStatusResponse(
    val id: Long,
    val name: String,
    val status: LampStatus
)
