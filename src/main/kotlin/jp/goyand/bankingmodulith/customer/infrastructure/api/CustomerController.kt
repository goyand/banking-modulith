package jp.goyand.bankingmodulith.customer.infrastructure.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jp.goyand.bankingmodulith.customer.application.query.CustomerQuery
import jp.goyand.bankingmodulith.customer.infrastructure.api.message.GetCustomerResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController(private val customerQuery: CustomerQuery) {
    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by id")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "A customer",
            content =
                [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = GetCustomerResponse::class),
                    )
                ],
        ),
        ApiResponse(responseCode = "404", description = "Customer not found"),
    )
    fun getCustomer(@PathVariable("id") id: String): GetCustomerResponse {
        return customerQuery.getCustomer(id)?.let { GetCustomerResponse.from(it) }
            ?: throw NoSuchElementException()
    }
}
