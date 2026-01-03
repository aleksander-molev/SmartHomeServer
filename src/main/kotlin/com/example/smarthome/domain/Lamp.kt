package com.example.smarthome.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "lamps")
class Lamp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String = DEFAULT_NAME,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: LampStatus = LampStatus.OFF
) {
    companion object {
        const val DEFAULT_NAME = "primary"
    }
}
