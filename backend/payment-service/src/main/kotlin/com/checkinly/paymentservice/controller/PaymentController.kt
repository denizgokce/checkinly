package com.checkinly.paymentservice.controller

import com.checkinly.paymentservice.model.Payment
import com.checkinly.paymentservice.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @GetMapping
    fun getAllPayments(): List<Payment> = paymentService.getAllPayments()

    @GetMapping("/{id}")
    fun getPaymentById(@PathVariable id: String): ResponseEntity<Payment> {
        return paymentService.getPaymentById(id).map { payment ->
            ResponseEntity.ok(payment)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createPayment(@RequestBody payment: Payment): Payment = paymentService.createPayment(payment)

    @PutMapping("/{id}")
    fun updatePayment(@PathVariable id: String, @RequestBody updatedPayment: Payment): ResponseEntity<Payment> {
        return paymentService.updatePayment(id, updatedPayment).map { payment ->
            ResponseEntity.ok(payment)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: String): ResponseEntity<Void> {
        return if (paymentService.deletePayment(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}