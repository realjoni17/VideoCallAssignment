/*
package com.joni.videocallassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val patientId: String,
    val patientName: String,
    val doctorId: String,
    val doctorName: String,
    val dateTime: Date,
    val status: AppointmentStatus = AppointmentStatus.BOOKED,
    val symptoms: String = "",
    val notes: String = ""
)


enum class AppointmentStatus {
    BOOKED,
    CONFIRMED,
    COMPLETED,
    CANCELLED
}*/
