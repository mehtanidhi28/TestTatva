package com.example.testtatva.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testtatva.R
import com.example.testtatva.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        mBinding.apply {
            btnTest1.setOnClickListener {
                startActivity(Intent(this@MainActivity, GridActivity::class.java))
            }
            btnTest2.setOnClickListener {

            }
        }
    }
}