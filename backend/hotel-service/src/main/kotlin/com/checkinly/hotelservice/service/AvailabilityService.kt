package com.checkinly.hotelservice.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class AvailabilityService(private val redisTemplate: RedisTemplate<String, Any>) {

    fun setRoomAvailability(hotelId: String, roomId: String, available: Boolean) {
        val key = "hotel:$hotelId:room:$roomId"
        redisTemplate.opsForValue().set(key, available)
    }

    fun getRoomAvailability(hotelId: String, roomId: String): Boolean {
        val key = "hotel:$hotelId:room:$roomId"
        return redisTemplate.opsForValue().get(key) as Boolean? ?: false
    }
}