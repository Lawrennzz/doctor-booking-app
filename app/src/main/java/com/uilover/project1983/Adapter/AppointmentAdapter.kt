package com.uilover.project1983.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project1983.Model.Appointment
import com.uilover.project1983.databinding.ItemAppointmentDetailBinding

class AppointmentAdapter(private val appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(val binding: ItemAppointmentDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.binding.apply {
            doctorNameTxt.text = "Doctor: ${appointment.doctorName}"
            specialtyTxt.text = "Specialty: ${appointment.specialty}"
            val dateTime = appointment.dateTime.split(" ")
            dateTxt.text = "Date: ${dateTime[0]}"
            timeTxt.text = "Time: ${dateTime[1]}"
            statusTxt.text = "Status: ${appointment.status}"
            patientNameTxt.text = "Patient: ${appointment.patientName}"
            notesTxt.text = "Notes: ${appointment.notes}"
            
            // Add new fields
            phoneTxt.text = "Phone: ${appointment.phone}"
            genderTxt.text = "Gender: ${appointment.gender}"
        }
    }

    override fun getItemCount() = appointments.size
}
