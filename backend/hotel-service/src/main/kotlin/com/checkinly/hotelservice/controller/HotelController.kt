package com.checkinly.hotelservice.controller

import com.checkinly.hotelservice.model.Hotel
import com.checkinly.hotelservice.service.HotelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hotels")
class HotelController(private val hotelService: HotelService) {

    @GetMapping
    fun getAllHotels(): List<Hotel> = hotelService.getAllHotels()

    @GetMapping("/{id}")
    fun getHotelById(@PathVariable id: String): ResponseEntity<Hotel> {
        return hotelService.getHotelById(id).map { hotel ->
            ResponseEntity.ok(hotel)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createHotel(@RequestBody hotel: Hotel): Hotel = hotelService.createHotel(hotel)

    @PutMapping("/{id}")
    fun updateHotel(@PathVariable id: String, @RequestBody updatedHotel: Hotel): ResponseEntity<Hotel> {
        return hotelService.updateHotel(id, updatedHotel).map { hotel ->
            ResponseEntity.ok(hotel)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteHotel(@PathVariable id: String): ResponseEntity<Void> {
        return if (hotelService.deleteHotel(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}