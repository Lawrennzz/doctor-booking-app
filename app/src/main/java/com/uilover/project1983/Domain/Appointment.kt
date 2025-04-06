package com.uilover.project1983.Domain

import android.os.Parcel
import android.os.Parcelable

data class Appointment(
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String,
    val status: String,
    val patientName: String,
    val notes: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(doctorName)
        parcel.writeString(specialty)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(status)
        parcel.writeString(patientName)
        parcel.writeString(notes)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Appointment> {
        override fun createFromParcel(parcel: Parcel): Appointment {
            return Appointment(parcel)
        }

        override fun newArray(size: Int): Array<Appointment?> {
            return arrayOfNulls(size)
        }
    }
}
