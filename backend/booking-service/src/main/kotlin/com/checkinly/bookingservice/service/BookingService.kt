package com.checkinly.bookingservice.service

import com.checkinly.bookingservice.model.Booking
import com.checkinly.bookingservice.repository.BookingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val kafkaProducerService: KafkaProducerService
) {

    fun getAllBookings(): List<Booking> = bookingRepository.findAll()

    fun getBookingById(id: String): Optional<Booking> = bookingRepository.findById(id)

    fun createBooking(booking: Booking): Booking {
        val savedBooking = bookingRepository.save(booking)
        kafkaProducerService.sendMessage("booking-updates", savedBooking)
        return savedBooking
    }

    fun updateBooking(id: String, updatedBooking: Booking): Optional<Booking> {
        return bookingRepository.findById(id).map { existingBooking ->
            val bookingToUpdate = existingBooking.copy(
                userId = updatedBooking.userId,
                hotelId = updatedBooking.hotelId,
                checkInDate = updatedBooking.checkInDate,
                checkOutDate = updatedBooking.checkOutDate
            )
            val savedBooking = bookingRepository.save(bookingToUpdate)
            kafkaProducerService.sendMessage("booking-updates", savedBooking)
            savedBooking
        }
    }

    fun deleteBooking(id: String): Boolean {
        return if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}