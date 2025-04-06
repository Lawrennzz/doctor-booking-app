package com.uilover.project1983.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uilover.project1983.Model.Appointment
import com.uilover.project1983.databinding.ActivityAppointmentConfirmationBinding

class AppointmentConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentConfirmationBinding
    private var appointment: Appointment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get appointment data from intent
        appointment = intent.getParcelableExtra<Appointment>("appointment")
        
        if (appointment != null) {
            binding.apply {
                doctorNameTxt.text = "Doctor: ${appointment!!.doctorName}"
                specialtyTxt.text = "Specialty: ${appointment!!.specialty}"
                dateTimeTxt.text = "Date & Time: ${appointment!!.dateTime}"
                statusTxt.text = "Status: ${appointment!!.status}"
                patientNameTxt.text = "Patient: ${appointment!!.patientName}"
                phoneTxt.text = "Phone: ${appointment!!.phone}"
                genderTxt.text = "Gender: ${appointment!!.gender}"
                notesTxt.text = "Notes: ${appointment!!.notes}"

                // Handle back button click
                backBtn.setOnClickListener { finish() }

                // Handle confirm button click
                confirmBtn.setOnClickListener {
                    Toast.makeText(this@AppointmentConfirmationActivity, "Appointment confirmed!", Toast.LENGTH_SHORT).show()
                    
                    // Here you would typically save the appointment to a database
                    // For now, we'll just finish the activity
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Failed to load appointment data", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
