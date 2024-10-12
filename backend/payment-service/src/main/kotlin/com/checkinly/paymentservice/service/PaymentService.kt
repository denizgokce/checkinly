package com.checkinly.paymentservice.service

import com.checkinly.paymentservice.model.Payment
import com.checkinly.paymentservice.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {

    fun getAllPayments(): List<Payment> = paymentRepository.findAll()

    fun getPaymentById(id: String): Optional<Payment> = paymentRepository.findById(id)

    fun createPayment(payment: Payment): Payment = paymentRepository.save(payment)

    fun updatePayment(id: String, updatedPayment: Payment): Optional<Payment> {
        return paymentRepository.findById(id).map { existingPayment ->
            val paymentToUpdate = existingPayment.copy(
                bookingId = updatedPayment.bookingId,
                amount = updatedPayment.amount,
                status = updatedPayment.status
            )
            paymentRepository.save(paymentToUpdate)
        }
    }

    fun deletePayment(id: String): Boolean {
        return if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}