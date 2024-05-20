package jp.goyand.bankingmodulith.customer.infrastructure.api.error

import jakarta.validation.ValidationException
import jp.goyand.bankingmodulith.customer.infrastructure.api.message.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(
        IllegalArgumentException::class,
        IllegalStateException::class,
        ValidationException::class,
    )
    fun handleIllegalArgumentException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse("400000", e.message ?: "Bad Request"))
    }
}
