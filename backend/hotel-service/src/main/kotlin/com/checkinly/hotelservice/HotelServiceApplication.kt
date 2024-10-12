package com.checkinly.hotelservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HotelServiceApplication

fun main(args: Array<String>) {
    runApplication<HotelServiceApplication>(*args)
}