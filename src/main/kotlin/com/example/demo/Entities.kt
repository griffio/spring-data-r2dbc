package com.example.demo

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table("Account")
data class Account(
    @Id
    val id: Long? = null,

    val name: String,

    val industry: String? = null,

    @Column("annual_revenue")
    val annualRevenue: BigDecimal? = null,

    val website: String? = null,

    val phone: String? = null,

    @Column("billing_street")
    val billingStreet: String? = null,

    @Column("billing_city")
    val billingCity: String? = null,

    @Column("billing_state")
    val billingState: String? = null,

    @Column("billing_postal_code")
    val billingPostalCode: String? = null,

    @Column("billing_country")
    val billingCountry: String? = null,

    @Column("shipping_street")
    val shippingStreet: String? = null,

    @Column("shipping_city")
    val shippingCity: String? = null,

    @Column("shipping_state")
    val shippingState: String? = null,

    @Column("shipping_postal_code")
    val shippingPostalCode: String? = null,

    @Column("shipping_country")
    val shippingCountry: String? = null,

    @CreatedDate
    @Column("created_date")
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_date")
    val lastModifiedDate: LocalDateTime? = null,
)

@Table("Opportunity")
data class Opportunity(
    @Id
    val id: Long? = null,

    @Column("account_id")
    val accountId: Long,

    val name: String,

    val stage: String,

    val amount: BigDecimal? = null,

    @Column("close_date")
    val closeDate: LocalDate? = null,

    val probability: Int? = null,

    val type: String? = null,

    @Column("lead_source")
    val leadSource: String? = null,

    val description: String? = null,

    @CreatedDate
    @Column("created_date")
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_date")
    val lastModifiedDate: LocalDateTime? = null,
)

@Table("Order_Table")
data class Order(
    @Id
    val id: Long? = null,

    @Column("account_id")
    val accountId: Long,

    @Column("opportunity_id")
    val opportunityId: Long? = null,

    @Column("order_number")
    val orderNumber: String,

    val status: String,

    @Column("total_amount")
    val totalAmount: BigDecimal,

    @Column("order_date")
    val orderDate: LocalDate,

    @Column("effective_date")
    val effectiveDate: LocalDate? = null,

    @Column("shipping_street")
    val shippingStreet: String? = null,

    @Column("shipping_city")
    val shippingCity: String? = null,

    @Column("shipping_state")
    val shippingState: String? = null,

    @Column("shipping_postal_code")
    val shippingPostalCode: String? = null,

    @Column("shipping_country")
    val shippingCountry: String? = null,

    @CreatedDate
    @Column("created_date")
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_date")
    val lastModifiedDate: LocalDateTime? = null,
)

@Table("Product")
data class Product(
    @Id
    val id: Long? = null,

    val name: String,

    @Column("product_code")
    val productCode: String? = null,

    val description: String? = null,

    @Column("list_price")
    val listPrice: BigDecimal? = null,

    @Column("is_active")
    val isActive: Boolean = true,

    @CreatedDate
    @Column("created_date")
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_date")
    val lastModifiedDate: LocalDateTime? = null,
    )

@Table("Order_Item")
data class OrderItem(
    @Id
    val id: Long? = null,

    @Column("order_id")
    val orderId: Long,

    @Column("product_id")
    val productId: Long,

    val quantity: Int,

    @Column("unit_price")
    val unitPrice: BigDecimal,

    @Column("total_price")
    val totalPrice: BigDecimal,

    val description: String? = null,

    @CreatedDate
    @Column("created_date")
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column("last_modified_date")
    val lastModifiedDate: LocalDateTime? = null,
)
