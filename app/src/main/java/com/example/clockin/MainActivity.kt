package com.example.clockin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.clockin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val CALL_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.mainActivity = this
    }

    fun onCallButtonClick(view: View) {
        if (isCallPermissionGranted()) {
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
        } else {
            requestCallPermission()
        }
    }

    // Check if CALL_PHONE permission is granted
    private fun isCallPermissionGranted(): Boolean {
        val permission = Manifest.permission.CALL_PHONE
        return ContextCompat.checkSelfPermission(
            this, permission) == PackageManager.PERMISSION_GRANTED
    }

    // Request CALL_PHONE permission
    private fun requestCallPermission() {
        val permission = Manifest.permission.CALL_PHONE
        ActivityCompat.requestPermissions(
            this, arrayOf(permission), CALL_PERMISSION_REQUEST_CODE)
    }

    // Handle permission request results
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted; you can now proceed with making the call
                onCallButtonClick(binding.callButton)
            } else {
                // Permission denied; handle this situation (e.g., show a message to the user)
            }
        }
    }
}