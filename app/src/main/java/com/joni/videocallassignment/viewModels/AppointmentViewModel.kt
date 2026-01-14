/*
package com.joni.videocallassignment.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.videocallassignment.models.Appointment
import com.joni.videocallassignment.models.AppointmentStatus

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class AppointmentViewModel : ViewModel() {
    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    private val _selectedAppointment = MutableStateFlow<Appointment?>(null)
    val selectedAppointment: StateFlow<Appointment?> = _selectedAppointment

    // Mock data
    init {
        loadMockAppointments()
    }

    private fun loadMockAppointments() {
        viewModelScope.launch {
            val mockData = listOf(
                Appointment(
                    patientId = "1",
                    patientName = "John Patient",
                    doctorId = "2",
                    doctorName = "Dr. Smith",
                    dateTime = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 10)
                        set(Calendar.MINUTE, 0)
                    }.time,
                    symptoms = "Chest pain and shortness of breath"
                ),
                Appointment(
                    patientId = "3",
                    patientName = "Sarah Wilson",
                    doctorId = "2",
                    doctorName = "Dr. Smith",
                    dateTime = Calendar.getInstance().apply {
                        add(Calendar.HOUR, 2)
                        set(Calendar.MINUTE, 30)
                    }.time,
                    symptoms = "Headache and dizziness"
                )
            )
            _appointments.value = mockData
        }
    }

    fun bookAppointment(
        patientId: String,
        patientName: String,
        doctorId: String,
        doctorName: String,
        dateTime: Date,
        symptoms: String
    ) {
        viewModelScope.launch {
            val newAppointment = Appointment(
                patientId = patientId,
                patientName = patientName,
                doctorId = doctorId,
                doctorName = doctorName,
                dateTime = dateTime,
                symptoms = symptoms
            )

            _appointments.value = _appointments.value + newAppointment
        }
    }

    fun updateAppointmentStatus(appointmentId: String, status: AppointmentStatus) {
        viewModelScope.launch {
            _appointments.value = _appointments.value.map { appointment ->
                if (appointment.id == appointmentId) {
                    appointment.copy(status = status)
                } else {
                    appointment
                }
            }
        }
    }

    fun selectAppointment(appointment: Appointment?) {
        _selectedAppointment.value = appointment
    }
}*/
