package com.example.smarthome.service

import com.example.smarthome.domain.InvalidLampCommandException
import com.example.smarthome.domain.Lamp
import com.example.smarthome.domain.LampCommandRequest
import com.example.smarthome.domain.LampStatus
import com.example.smarthome.repository.LampRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LampService(
    private val lampRepository: LampRepository,
    private val deviceControlService: DeviceControlService
) {

    private val logger = LoggerFactory.getLogger(LampService::class.java)

    @Transactional
    fun handleCommand(request: LampCommandRequest): Lamp {
        val desiredStatus = try {
            LampStatus.from(request.command)
        } catch (ex: InvalidLampCommandException) {
            logger.warn("Received invalid lamp command: {}", request.command)
            throw ex
        }

        val lamp = lampRepository.findByName(Lamp.DEFAULT_NAME).orElseGet {
            logger.info("Lamp not found in database, creating default entry")
            lampRepository.save(Lamp(name = Lamp.DEFAULT_NAME))
        }

        if (lamp.status == desiredStatus) {
            logger.info("Lamp {} already in status {}", lamp.name, lamp.status)
            return lamp
        }

        deviceControlService.applyStatus(desiredStatus)
        lamp.status = desiredStatus
        return lampRepository.save(lamp)
    }
}
