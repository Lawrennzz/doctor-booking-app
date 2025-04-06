package com.uilover.project1983.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Appointment(
    val id: String = UUID.randomUUID().toString(),
    val doctorName: String,
    val specialty: String,
    val dateTime: String,
    val status: String,
    val patientName: String,
    val phone: String,
    val gender: String,
    val notes: String,
    val privacyAgreed: Boolean
) : Parcelable
