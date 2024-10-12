package com.checkinly.hotelservice.service

import com.checkinly.hotelservice.model.Hotel
import com.checkinly.hotelservice.repository.HotelRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class HotelService(private val hotelRepository: HotelRepository) {

    fun getAllHotels(): List<Hotel> = hotelRepository.findAll()

    fun getHotelById(id: String): Optional<Hotel> = hotelRepository.findById(id)

    fun createHotel(hotel: Hotel): Hotel = hotelRepository.save(hotel)

    fun updateHotel(id: String, updatedHotel: Hotel): Optional<Hotel> {
        return hotelRepository.findById(id).map { existingHotel ->
            val hotelToUpdate = existingHotel.copy(
                name = updatedHotel.name,
                address = updatedHotel.address,
                rating = updatedHotel.rating
            )
            hotelRepository.save(hotelToUpdate)
        }
    }

    fun deleteHotel(id: String): Boolean {
        return if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}