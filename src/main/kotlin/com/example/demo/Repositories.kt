package com.example.demo.repository

import com.example.demo.Account
import com.example.demo.Opportunity
import com.example.demo.Order
import com.example.demo.OrderItem
import com.example.demo.Product
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.math.BigDecimal
import java.time.LocalDate

interface AccountRepository : CoroutineCrudRepository<Account, Long> {
    fun findByName(name: String): Flow<Account>
    fun findByIndustry(industry: String): Flow<Account>
    fun findByAnnualRevenueGreaterThan(revenue: BigDecimal): Flow<Account>
}

interface OpportunityRepository : CoroutineCrudRepository<Opportunity, Long> {
    fun findByAccountId(accountId: Long): Flow<Opportunity>
    fun findByStage(stage: String): Flow<Opportunity>
    fun findByCloseDateBetween(startDate: LocalDate, endDate: LocalDate): Flow<Opportunity>
    fun findByAmountGreaterThan(amount: BigDecimal): Flow<Opportunity>

    @Query("SELECT * FROM Opportunity WHERE probability > :minProbability AND amount > :minAmount")
    fun findHighValueOpportunities(minProbability: Int, minAmount: BigDecimal): Flow<Opportunity>
}

interface OrderRepository : CoroutineCrudRepository<Order, Long> {
    fun findByAccountId(accountId: Long): Flow<Order>
    fun findByOpportunityId(opportunityId: Long): Flow<Order>
    fun findByStatus(status: String): Flow<Order>
    fun findByOrderDateBetween(startDate: LocalDate, endDate: LocalDate): Flow<Order>
    fun findByTotalAmountGreaterThan(amount: BigDecimal): Flow<Order>

    @Query("SELECT * FROM Order_Table WHERE account_id = :accountId ORDER BY order_date DESC LIMIT :limit")
    fun findRecentOrdersByAccount(accountId: Long, limit: Int): Flow<Order>
}

interface ProductRepository : CoroutineCrudRepository<Product, Long> {
    fun findByName(name: String): Flow<Product>
    suspend fun findByProductCode(productCode: String): Product?
    fun findByIsActive(isActive: Boolean): Flow<Product>
    fun findByListPriceBetween(minPrice: BigDecimal, maxPrice: BigDecimal): Flow<Product>

    @Query("SELECT p.* FROM Product p JOIN Order_Item oi ON p.id = oi.product_id GROUP BY p.id ORDER BY COUNT(oi.id) DESC LIMIT :limit")
    fun findTopSellingProducts(limit: Int): Flow<Product>
}

interface OrderItemRepository : CoroutineCrudRepository<OrderItem, Long> {
    fun findByOrderId(orderId: Long): Flow<OrderItem>
    fun findByProductId(productId: Long): Flow<OrderItem>

    @Query("SELECT SUM(quantity) FROM Order_Item WHERE product_id = :productId")
    suspend fun getTotalQuantitySoldForProduct(productId: Long): Int?

    @Query("SELECT SUM(total_price) FROM Order_Item WHERE order_id = :orderId")
    suspend fun getTotalAmountForOrder(orderId: Long): BigDecimal?
}
