package com.checkinly.bookingservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "bookings")
data class Booking(
    @Id
    val id: String? = null,
    val userId: String,
    val hotelId: String,
    val roomId: String,
    val checkInDate: String,
    val checkOutDate: String
)