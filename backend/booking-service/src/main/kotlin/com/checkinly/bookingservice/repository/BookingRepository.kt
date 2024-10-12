package com.checkinly.bookingservice.repository

import com.checkinly.bookingservice.model.Booking
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepository : MongoRepository<Booking, String>