package jp.goyand.bankingmodulith.customer.infrastructure.api.error

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
}
