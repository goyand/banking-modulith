package jp.goyand.bankingmodulith.customer.infrastructure

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.spring.api.DBRider
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBRider
@DataSet(value = ["datasets/customer/common/customer.yml"])
class CustomerControllerTest(@Autowired val mockMvc: MockMvc) {
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
