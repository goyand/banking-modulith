package jp.goyand.bankingmodulith.customer.infrastructure

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.spring.api.DBRider
import io.mockk.every
import io.mockk.mockkObject
import jp.goyand.bankingmodulith.common.entity.NumberGenerator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBRider
class CustomerControllerTest(@Autowired val mockMvc: MockMvc) {

    @Nested
    @DataSet(value = ["datasets/customer/common/customer.yml"])
    inner class GetCustomer {
        @Test
        fun `should get customer`() {
            // execute & verify
            mockMvc
                .perform(get("/api/v1/customers/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.customerNumber").value("cus-001"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.middleName").value("Charles"))
        }

        @Test
        fun `should throw 404 when customer not found`() {
            // execute & verify
            mockMvc.perform(get("/api/v1/customers/dummy")).andExpect(status().isNotFound)
        }
    }

    @Nested
    inner class CreateCustomer {

        private val customerNumber = "C-0000001"

        @BeforeEach
        fun setUp() {
            mockkObject(NumberGenerator)
            every { NumberGenerator.generateNumber("C") } returns customerNumber
        }

        @Test
        fun `should create customer`() {
            // execute & verify
            mockMvc
                .perform(
                    post("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            """
                            {
                                "firstName": "Jane",
                                "lastName": "Doe",
                                "middleName": "Alice"
                            }
                            """.trimIndent()
                        )
                )
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id").isNotEmpty)
                .andExpect(jsonPath("$.customerNumber").value(customerNumber))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.middleName").value("Alice"))
        }

        @Test
        fun `should throw 400 when invalid request`() {
            // execute & verify
            mockMvc
                .perform(
                    post("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            """
                            {
                                "firstName": "Jane",
                                "lastName": "",
                                "middleName": "Alice"
                            }
                            """.trimIndent()
                        )
                )
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.code").value("400000"))
                .andExpect(jsonPath("$.message").value("Last name must not be blank"))
        }
    }
}
