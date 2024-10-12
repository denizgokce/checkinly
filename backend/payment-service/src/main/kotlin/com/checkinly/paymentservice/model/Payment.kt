package com.checkinly.paymentservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "payments")
data class Payment(
    @Id
    val id: String? = null,
    val bookingId: String,
    val amount: Double,
    val status: String
)