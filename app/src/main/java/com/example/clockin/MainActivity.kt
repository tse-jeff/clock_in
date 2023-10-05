package com.example.clockin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.clockin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.mainActivity = this
    }

    fun onCallButtonClick(view: View) {
        val phoneNumber = binding.phoneNumber.text.toString()

        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")

        try {
            startActivity(callIntent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

        val num1 = binding.numInput.text.toString()
        val num2 = binding.numInput2.text.toString()
    }
}