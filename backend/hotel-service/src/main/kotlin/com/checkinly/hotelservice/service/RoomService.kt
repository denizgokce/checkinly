package com.checkinly.hotelservice.service

import com.checkinly.hotelservice.model.Room
import com.checkinly.hotelservice.repository.RoomRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun getAllRooms(): List<Room> = roomRepository.findAll()

    fun getRoomsByHotelId(hotelId: String): List<Room> = roomRepository.findByHotelId(hotelId)

    fun getRoomById(id: String): Optional<Room> = roomRepository.findById(id)

    fun createRoom(room: Room): Room {
        val savedRoom = roomRepository.save(room)
        setRoomAvailabilityInRedis(savedRoom)
        return savedRoom
    }

    fun updateRoom(id: String, updatedRoom: Room): Optional<Room> {
        return roomRepository.findById(id).map { existingRoom ->
            val roomToUpdate = existingRoom.copy(
                roomNumber = updatedRoom.roomNumber,
                type = updatedRoom.type,
                price = updatedRoom.price,
                available = updatedRoom.available
            )
            val savedRoom = roomRepository.save(roomToUpdate)
            setRoomAvailabilityInRedis(savedRoom)
            savedRoom
        }
    }

    fun deleteRoom(id: String): Boolean {
        return if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id)
            removeRoomAvailabilityFromRedis(id)
            true
        } else {
            false
        }
    }

    fun getAvailableRoomsByHotelId(hotelId: String): List<Room> {
        val rooms = roomRepository.findByHotelId(hotelId)
        return rooms.filter { isRoomAvailableInRedis(hotelId, it.id!!) }
    }

    private fun setRoomAvailabilityInRedis(room: Room) {
        val key = "hotel:${room.hotelId}:room:${room.id}"
        redisTemplate.opsForValue().set(key, room.available)
    }

    private fun removeRoomAvailabilityFromRedis(roomId: String) {
        val keyPattern = "hotel:*:room:$roomId"
        val keys = redisTemplate.keys(keyPattern)
        keys?.forEach { redisTemplate.delete(it) }
    }

    private fun isRoomAvailableInRedis(hotelId: String, roomId: String): Boolean {
        val key = "hotel:$hotelId:room:$roomId"
        return redisTemplate.opsForValue().get(key) as Boolean? ?: false
    }
}