package com.checkinly.hotelservice.controller

import com.checkinly.hotelservice.model.Room
import com.checkinly.hotelservice.service.RoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rooms")
class RoomController(private val roomService: RoomService) {

    @GetMapping
    fun getAllRooms(): List<Room> = roomService.getAllRooms()

    @GetMapping("/hotel/{hotelId}")
    fun getRoomsByHotelId(@PathVariable hotelId: String): List<Room> = roomService.getRoomsByHotelId(hotelId)

    @GetMapping("/hotel/{hotelId}/available")
    fun getAvailableRoomsByHotelId(@PathVariable hotelId: String): List<Room> = roomService.getAvailableRoomsByHotelId(hotelId)

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: String): ResponseEntity<Room> {
        return roomService.getRoomById(id).map { room ->
            ResponseEntity.ok(room)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createRoom(@RequestBody room: Room): Room = roomService.createRoom(room)

    @PutMapping("/{id}")
    fun updateRoom(@PathVariable id: String, @RequestBody updatedRoom: Room): ResponseEntity<Room> {
        return roomService.updateRoom(id, updatedRoom).map { room ->
            ResponseEntity.ok(room)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteRoom(@PathVariable id: String): ResponseEntity<Void> {
        return if (roomService.deleteRoom(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}