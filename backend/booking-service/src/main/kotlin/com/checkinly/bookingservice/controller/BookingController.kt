package com.checkinly.bookingservice.controller

import com.checkinly.bookingservice.model.Booking
import com.checkinly.bookingservice.service.BookingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookings")
class BookingController(private val bookingService: BookingService) {

    @GetMapping
    fun getAllBookings(): List<Booking> = bookingService.getAllBookings()

    @GetMapping("/{id}")
    fun getBookingById(@PathVariable id: String): ResponseEntity<Booking> {
        return bookingService.getBookingById(id).map { booking ->
            ResponseEntity.ok(booking)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createBooking(@RequestBody booking: Booking): Booking = bookingService.createBooking(booking)

    @PutMapping("/{id}")
    fun updateBooking(@PathVariable id: String, @RequestBody updatedBooking: Booking): ResponseEntity<Booking> {
        return bookingService.updateBooking(id, updatedBooking).map { booking ->
            ResponseEntity.ok(booking)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteBooking(@PathVariable id: String): ResponseEntity<Void> {
        return if (bookingService.deleteBooking(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}