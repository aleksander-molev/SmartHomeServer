package com.example.smarthome.repository

import com.example.smarthome.domain.Lamp
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LampRepository : JpaRepository<Lamp, Long> {
    fun findByName(name: String): Optional<Lamp>
}
