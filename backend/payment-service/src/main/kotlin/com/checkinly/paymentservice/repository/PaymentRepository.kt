package com.checkinly.paymentservice.repository

import com.checkinly.paymentservice.model.Payment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : MongoRepository<Payment, String>