package com.example.demo

import com.example.demo.repository.AccountRepository
import com.example.demo.repository.OrderRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import kotlin.test.assertEquals

@DataR2dbcTest()
class DemoApplicationTests(
    @Autowired val accountRepository: AccountRepository,
    @Autowired val orderRepository: OrderRepository,
) {
    @Test
    fun makeOrder() = runTest {
        val rndAccount = createRandomAccount(null)
        val newAccount = accountRepository.save(rndAccount)
        assertEquals(newAccount, accountRepository.findByName(newAccount.name).first())
    }
}
