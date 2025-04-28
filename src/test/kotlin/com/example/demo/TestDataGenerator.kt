package com.example.demo.util

import com.example.demo.Account
import com.example.demo.Opportunity
import com.example.demo.Order
import com.example.demo.OrderItem
import com.example.demo.Product
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

private val industries = listOf(
    "Technology", "Healthcare", "Financial Services", "Manufacturing",
    "Retail", "Education", "Energy", "Telecommunications", "Hospitality",
    "Transportation", "Media", "Construction", "Agriculture", "Consulting"
)

private val domains = listOf("example.com", "testcorp.org", "acme.co", "globex.net", "initech.io")

private val cities = listOf(
    "New York", "San Francisco", "Chicago", "Boston", "Seattle",
    "Austin", "Denver", "Atlanta", "Miami", "Los Angeles"
)

private val states = listOf("CA", "NY", "TX", "FL", "IL", "WA", "MA", "CO", "GA", "OR")

private val countries = listOf("USA", "Canada", "UK", "Germany", "France", "Australia", "Japan")

private val opportunityStages = listOf(
    "Prospecting", "Qualification", "Needs Analysis", "Value Proposition",
    "Negotiation", "Closed Won", "Closed Lost"
)

private val orderStatuses = listOf(
    "Draft", "Pending Approval", "Activated", "Completed", "Cancelled"
)

private val productNames = listOf(
    "Premium Widget", "Enterprise Software License", "Cloud Storage Plan",
    "Professional Services", "Support Package", "Hardware Bundle",
    "Training Package", "Security Suite", "Analytics Platform",
    "Mobile App Subscription", "IoT Device", "Data Migration Service"
)

fun createRandomAccount(id: Long? = null): Account {
    val name = "Company ${('A'..'Z').random()}${Random.nextInt(1000)}"
    val industry = industries.random()
    val revenue = BigDecimal(Random.nextDouble(10000.0, 10000000.0)).setScale(2, RoundingMode.HALF_UP)
    val city = cities.random()
    val state = states.random()
    val country = countries.random()
    val domain = domains.random()

    return Account(
        id = id,
        name = name,
        industry = industry,
        annualRevenue = revenue,
        website = "https://www.${name.lowercase().replace(" ", "")}.${domain}",
        phone = "+1-${Random.nextInt(100, 999)}-${Random.nextInt(100, 999)}-${Random.nextInt(1000, 9999)}",
        billingStreet = "${Random.nextInt(100, 9999)} ${('A'..'Z').random()} Street",
        billingCity = city,
        billingState = state,
        billingPostalCode = "${Random.nextInt(10000, 99999)}",
        billingCountry = country,
        shippingStreet = "${Random.nextInt(100, 9999)} ${('A'..'Z').random()} Avenue",
        shippingCity = city,
        shippingState = state,
        shippingPostalCode = "${Random.nextInt(10000, 99999)}",
        shippingCountry = country,
        createdDate = LocalDateTime.now().minusDays(Random.nextLong(1, 365)),
        lastModifiedDate = LocalDateTime.now()
    )
}

/**
 * Generate a list of random Accounts
 */
fun createRandomAccounts(count: Int): List<Account> {
    return (1..count).map { createRandomAccount() }
}

/**
 * Generate a random Opportunity for a given Account
 */
fun createRandomOpportunity(accountId: Long, id: Long? = null): Opportunity {
    val stage = opportunityStages.random()
    val isWon = stage == "Closed Won"
    val probability = when (stage) {
        "Prospecting" -> Random.nextInt(10, 30)
        "Qualification" -> Random.nextInt(20, 40)
        "Needs Analysis" -> Random.nextInt(30, 50)
        "Value Proposition" -> Random.nextInt(40, 70)
        "Negotiation" -> Random.nextInt(60, 90)
        "Closed Won" -> 100
        "Closed Lost" -> 0
        else -> Random.nextInt(50)
    }

    val amount = BigDecimal(Random.nextDouble(5000.0, 500000.0)).setScale(2, RoundingMode.HALF_UP)
    val closeDate = LocalDate.now().plusDays(Random.nextLong(1, 180))

    return Opportunity(
        id = id,
        accountId = accountId,
        name = "${createRandomAccount().name} - ${productNames.random()}",
        stage = stage,
        amount = amount,
        closeDate = closeDate,
        probability = probability,
        type = if (Random.nextBoolean()) "New Business" else "Existing Business",
        leadSource = listOf("Web", "Phone Inquiry", "Partner Referral", "Conference", "Other").random(),
        description = "Opportunity for ${productNames.random()} - ${Random.nextInt(10000)}",
        createdDate = LocalDateTime.now().minusDays(Random.nextLong(1, 90)),
        lastModifiedDate = LocalDateTime.now()
    )
}

/**
 * Generate random Opportunities for an Account
 */
fun createRandomOpportunities(accountId: Long, count: Int): List<Opportunity> {
    return (1..count).map { createRandomOpportunity(accountId) }
}

fun createRandomProduct(id: Long? = null): Product {
    val name = productNames.random()
    val price = BigDecimal(Random.nextDouble(100.0, 10000.0)).setScale(2, RoundingMode.HALF_UP)

    return Product(
        id = id,
        name = name,
        productCode = "PROD-${Random.nextInt(1000, 9999)}",
        description = "This is a ${name.lowercase()} product for enterprise use",
        listPrice = price,
        isActive = Random.nextDouble() > 0.1, // 90% chance of being active
        createdDate = LocalDateTime.now().minusDays(Random.nextLong(1, 365)),
        lastModifiedDate = LocalDateTime.now(),
    )
}

fun createRandomProducts(count: Int): List<Product> {
    return (1..count).map { createRandomProduct() }
}

/**
 * Generate a random Order for an Account
 */
fun createRandomOrder(
    accountId: Long,
    opportunityId: Long? = null,
    id: Long? = null,
): Order {
    val orderDate = LocalDate.now().minusDays(Random.nextLong(0, 60))
    val effectiveDate = if (Random.nextBoolean()) orderDate.plusDays(Random.nextLong(1, 30)) else null
    val status = orderStatuses.random()

    // We'll set a placeholder for total amount, which would normally be calculated from order items
    val totalAmount = BigDecimal.ZERO

    val city = cities.random()
    val state = states.random()
    val country = countries.random()

    return Order(
        id = id,
        accountId = accountId,
        opportunityId = opportunityId,
        orderNumber = "ORD-${Random.nextInt(10000, 99999)}",
        status = status,
        totalAmount = totalAmount, // This will be updated when order items are added
        orderDate = orderDate,
        effectiveDate = effectiveDate,
        shippingStreet = "${Random.nextInt(100, 9999)} ${('A'..'Z').random()} Street",
        shippingCity = city,
        shippingState = state,
        shippingPostalCode = "${Random.nextInt(10000, 99999)}",
        shippingCountry = country,
        createdDate = LocalDateTime.now().minusDays(Random.nextLong(1, 90)),
        lastModifiedDate = LocalDateTime.now()
    )
}

fun createRandomOrderItem(
    orderId: Long,
    productId: Long,
    id: Long? = null,
): OrderItem {
    val quantity = Random.nextInt(1, 10)
    val unitPrice = BigDecimal(Random.nextDouble(100.0, 5000.0)).setScale(2, RoundingMode.HALF_UP)
    val totalPrice = unitPrice.multiply(BigDecimal(quantity))

    return OrderItem(
        id = id,
        orderId = orderId,
        productId = productId,
        quantity = quantity,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        description = "Order item for product $productId",
        createdDate = LocalDateTime.now().minusDays(Random.nextLong(0, 30)),
        lastModifiedDate = LocalDateTime.now()
    )
}

/**
 * Generate a complete Order with OrderItems
 */
fun createCompleteOrder(
    accountId: Long,
    opportunityId: Long? = null,
    products: List<Product>,
    orderId: Long? = null,
): Pair<Order, List<OrderItem>> {
    // Create the order first
    val order = createRandomOrder(accountId, opportunityId, orderId)

    // Select a random number of products for this order
    val orderProducts = products.shuffled().take(Random.nextInt(1, minOf(5, products.size + 1)))

    // Create order items for each product
    val orderItems = orderProducts.map { product ->
        createRandomOrderItem(order.id ?: 0L, product.id ?: 0L)
    }

    // Calculate the total amount
    val totalAmount = orderItems.sumOf { it.totalPrice }

    // Update the order with the correct total amount
    val updatedOrder = order.copy(totalAmount = totalAmount)

    return Pair(updatedOrder, orderItems)
}

/**
 * Generate a complete set of test data including accounts, opportunities, products, orders, and order items
 */
fun createCompleteTestDataSet(
    accountCount: Int = 5,
    opportunitiesPerAccount: Int = 2,
    productsCount: Int = 10,
    ordersPerAccount: Int = 3,
): TestDataSet {
    val accounts = createRandomAccounts(accountCount)
    val products = createRandomProducts(productsCount)

    val opportunities = accounts.flatMap { account ->
        createRandomOpportunities(account.id ?: 0L, opportunitiesPerAccount)
    }

    val ordersWithItems = accounts.flatMap { account ->
        (1..ordersPerAccount).map {
            // Randomly associate with an opportunity 50% of the time
            val accountOpportunities = opportunities.filter { it.accountId == account.id }
            val opportunityId = if (accountOpportunities.isNotEmpty() && Random.nextBoolean()) {
                accountOpportunities.random().id
            } else {
                null
            }

            createCompleteOrder(account.id ?: 0L, opportunityId, products)
        }
    }

    val orders = ordersWithItems.map { it.first }
    val orderItems = ordersWithItems.flatMap { it.second }

    return TestDataSet(
        accounts = accounts,
        opportunities = opportunities,
        products = products,
        orders = orders,
        orderItems = orderItems
    )
}

/**
 * Data class to hold a complete set of test data
 */
data class TestDataSet(
    val accounts: List<Account>,
    val opportunities: List<Opportunity>,
    val products: List<Product>,
    val orders: List<Order>,
    val orderItems: List<OrderItem>,
)

