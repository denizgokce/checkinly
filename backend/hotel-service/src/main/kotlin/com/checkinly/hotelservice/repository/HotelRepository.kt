package com.checkinly.hotelservice.repository

import com.checkinly.hotelservice.model.Hotel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HotelRepository : MongoRepository<Hotel, String>