package com.checkinly.hotelservice.kafka

import com.checkinly.hotelservice.model.Booking
import com.checkinly.hotelservice.service.AvailabilityService
import com.checkinly.hotelservice.service.RoomService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class BookingConsumer(
    private val availabilityService: AvailabilityService,
    private val roomService: RoomService
) {

    @KafkaListener(topics = ["booking-updates"], groupId = "hotel-service")
    fun consume(booking: Booking) {
        val hotelId = booking.hotelId
        val roomId = booking.roomId

        // Update Redis cache
        availabilityService.setRoomAvailability(hotelId, roomId, false)

        // Update database
        roomService.getRoomById(roomId).ifPresent { room ->
            val updatedRoom = room.copy(available = false)
            roomService.updateRoom(roomId, updatedRoom)
        }
    }
}