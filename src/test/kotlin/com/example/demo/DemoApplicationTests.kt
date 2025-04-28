package com.example.demo

import com.example.demo.repository.AccountRepository
import com.example.demo.repository.OrderItemRepository
import com.example.demo.repository.OrderRepository
import com.example.demo.repository.ProductRepository
import com.example.demo.util.createCompleteOrder
import com.example.demo.util.createRandomAccount
import com.example.demo.util.createRandomProducts
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataR2dbcTest()
class DemoApplicationTests(
    @Autowired val accountRepository: AccountRepository,
    @Autowired val orderRepository: OrderRepository,
    @Autowired val productRepository: ProductRepository,
    @Autowired val orderItemRepository: OrderItemRepository,
) {
    @Test
    fun makeOrder() = runTest {
        val account = createRandomAccount()
        val savedAccount = accountRepository.save(account)

        val products = createRandomProducts(3)
        val savedProducts = products.map { productRepository.save(it) }

        val (order, orderItems) = createCompleteOrder(
            accountId = savedAccount.id!!,
            products = savedProducts
        )

        val savedOrder = orderRepository.save(order)
        orderItems.forEach { item ->
            val itemWithOrderId = item.copy(orderId = savedOrder.id!!)
            orderItemRepository.save(itemWithOrderId)
        }

        val retrievedOrder = orderRepository.findById(savedOrder.id!!)
        assertNotNull(retrievedOrder)
        assertEquals(savedAccount.id, retrievedOrder.accountId)
    }
}
