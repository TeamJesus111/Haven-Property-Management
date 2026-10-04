package com.example.mobileappproj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileappproj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View/Data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up SAVE button click listener
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
                binding.tenantResultTextView.text = "Please fill in all fields!"
            } else {
                binding.tenantResultTextView.text =
                    "Tenant Details:\nName: $name\nPhone: $phone\nRent Paid: $$rent"
            }
        }
    }
}