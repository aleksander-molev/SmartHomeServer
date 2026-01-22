package com.example.smarthome.controller

import com.example.smarthome.domain.LampCommandRequest
import com.example.smarthome.domain.LampStatusResponse
import com.example.smarthome.service.LampService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lamp")
class LampController(
    private val lampService: LampService
) {

    @PostMapping("/command")
    fun command(@Valid @RequestBody request: LampCommandRequest): ResponseEntity<LampStatusResponse> {
        val lamp = lampService.handleCommand(request)
        return ResponseEntity.ok(
            LampStatusResponse(
                id = lamp.id!!,
                name = lamp.name,
                status = lamp.status
            )
        )
    }
}
