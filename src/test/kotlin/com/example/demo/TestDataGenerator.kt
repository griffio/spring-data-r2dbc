package com.example.demo

import java.math.BigDecimal
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

fun createRandomAccount(id: Long? = null): Account {
    val name = "Company ${('A'..'Z').random()}${Random.nextInt(1000)}"
    val industry = industries.random()
    val revenue = BigDecimal(Random.nextDouble(10000.0, 10000000.0)).setScale(2, BigDecimal.ROUND_HALF_UP)
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

fun createRandomAccounts(count: Int): List<Account> {
    return (1..count).map { createRandomAccount() }
}
