package com.uilover.project1983.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uilover.project1983.Model.Appointment
import com.uilover.project1983.databinding.ActivityAppointmentBinding
import com.uilover.project1983.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    private var doctorId: String = ""
    private var doctorName: String = ""
    private var doctorSpecialty: String = ""
    private var selectedGender: String = ""
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from intent
        doctorId = intent.getStringExtra("Id") ?: ""
        doctorName = intent.getStringExtra("Name") ?: ""
        doctorSpecialty = intent.getStringExtra("Special") ?: ""

        // Set up UI
        binding.apply {
            doctorNameTxt.text = doctorName
            specialtyTxt.text = doctorSpecialty

            // Handle back button click
            backBtn.setOnClickListener { finish() }

            // Handle gender selection
            genderGroup.setOnCheckedChangeListener { _, checkedId ->
                selectedGender = when (checkedId) {
                    R.id.maleRadio -> "Male"
                    R.id.femaleRadio -> "Female"
                    else -> ""
                }
            }

            // Handle date selection
            dateTxt.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@AppointmentActivity,
                    { _, year, monthOfYear, dayOfMonth ->
                        calendar.set(year, monthOfYear, dayOfMonth)
                        selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
                        dateTxt.setText(selectedDate)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }

            // Handle time selection
            timeTxt.setOnClickListener {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(
                    this@AppointmentActivity,
                    { _, hourOfDay, minute ->
                        selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                        timeTxt.setText(selectedTime)
                    },
                    hour,
                    minute,
                    true
                )
                timePickerDialog.show()
            }

            // Handle booking button click
            bookBtn.setOnClickListener {
                val patientName = patientNameTxt.text.toString()
                val phone = phoneTxt.text.toString()
                val notes = notesTxt.text.toString()
                val privacyAgreed = privacyCheck.isChecked

                // Validate inputs
                if (patientName.isEmpty()) {
                    Toast.makeText(this@AppointmentActivity, "Please enter your name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (phone.isEmpty()) {
                    Toast.makeText(this@AppointmentActivity, "Please enter your phone number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // More flexible phone number validation
                if (!phone.matches(Regex("^[\\+]?[0-9\\s-]{8,}$"))) {
                    Toast.makeText(this@AppointmentActivity, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (selectedGender.isEmpty()) {
                    Toast.makeText(this@AppointmentActivity, "Please select your gender", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (selectedDate.isEmpty()) {
                    Toast.makeText(this@AppointmentActivity, "Please select a date", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (selectedTime.isEmpty()) {
                    Toast.makeText(this@AppointmentActivity, "Please select a time", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (!privacyAgreed) {
                    Toast.makeText(this@AppointmentActivity, "Please agree to the privacy policy", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Create appointment object
                val appointment = Appointment(
                    doctorName = doctorName,
                    specialty = doctorSpecialty,
                    dateTime = "$selectedDate $selectedTime",
                    status = "Pending",
                    patientName = patientName,
                    phone = phone,
                    gender = selectedGender,
                    notes = notes,
                    privacyAgreed = privacyAgreed
                )

                // Pass appointment data to next activity
                val intent = Intent(this@AppointmentActivity, AppointmentConfirmationActivity::class.java)
                intent.putExtra("appointment", appointment)
                
                // Clear the back stack to prevent going back to DetailActivity
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish() // Finish this activity to prevent going back to it
            }
        }
    }
}
