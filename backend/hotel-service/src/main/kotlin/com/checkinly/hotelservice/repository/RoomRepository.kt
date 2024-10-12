package com.checkinly.hotelservice.repository

import com.checkinly.hotelservice.model.Room
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : MongoRepository<Room, String> {
    fun findByHotelId(hotelId: String): List<Room>
}