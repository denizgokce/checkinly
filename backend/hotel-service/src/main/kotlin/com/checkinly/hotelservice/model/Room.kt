package com.checkinly.hotelservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rooms")
data class Room(
    @Id
    val id: String? = null,
    val hotelId: String,
    val roomNumber: String,
    val type: String,
    val price: Double,
    val available: Boolean
)