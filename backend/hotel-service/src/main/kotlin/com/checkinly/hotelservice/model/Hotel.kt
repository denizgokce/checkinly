package com.checkinly.hotelservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "hotels")
data class Hotel(
    @Id
    val id: String? = null,
    val name: String,
    val address: String,
    val rating: Double,
    val rooms: List<Room> = listOf()
)