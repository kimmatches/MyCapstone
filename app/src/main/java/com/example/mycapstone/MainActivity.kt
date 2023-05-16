package com.example.mycapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var qrCodeScan = QRCodeScan(this)
//        var mainActivity2 = MainActivity2()

        /** Click */
        binding.tvQrScan.setOnClickListener {
            qrCodeScan.startQRScan()


        }
    }
}
