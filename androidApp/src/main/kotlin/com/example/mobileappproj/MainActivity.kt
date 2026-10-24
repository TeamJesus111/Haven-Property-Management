package com.example.mobileappproj
import android.util.Log
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileappproj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        // Initialize View/Data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // RESTORE STATE
        if (savedInstanceState != null) {
            val name = savedInstanceState.getString("tenant_name")
            val phone = savedInstanceState.getString("tenant_phone")
            val rent = savedInstanceState.getString("tenant_rent")
            if (name != null && phone != null && rent != null) {
                val tenant = Tenant(name, phone, rent)
                lastTenant = tenant
                binding.tenant = tenant
            }
        }

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
                // LIFECYCLE METHODS
                override fun onStart() {
                    super.onStart()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
                }

                override fun onResume() {
                    super.onResume()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
                }

                override fun onPause() {
                    super.onPause()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
                }

                override fun onStop() {
                    super.onStop()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
                }

                override fun onRestart() {
                    super.onRestart()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
                }

                override fun onDestroy() {
                    super.onDestroy()
                    Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
                }


            }

        }
    }
}