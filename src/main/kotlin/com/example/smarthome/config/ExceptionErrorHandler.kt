package com.example.smarthome.config

import com.example.smarthome.domain.InvalidLampCommandException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.Instant

@RestControllerAdvice
class ExceptionErrorHandler {

    private val logger = LoggerFactory.getLogger(ExceptionErrorHandler::class.java)

    @ExceptionHandler(InvalidLampCommandException::class)
    fun handleInvalidLampCommand(
        exception: InvalidLampCommandException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        logger.warn("Invalid lamp command received", exception)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                message = exception.message ?: "Invalid command",
                path = request.getDescription(false)
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(
        exception: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = exception.bindingResult.fieldErrors.associate(FieldError::getField, FieldError::getDefaultMessage)
        logger.warn("Validation failed for request: {}", errors)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                message = "Validation failed",
                path = request.getDescription(false),
                details = errors
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        logger.error("Unexpected error", exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                message = "Unexpected error occurred",
                path = request.getDescription(false)
            )
        )
    }
}

data class ErrorResponse(
    val message: String,
    val path: String,
    val timestamp: Instant = Instant.now(),
    val details: Map<String, String?>? = null
)
