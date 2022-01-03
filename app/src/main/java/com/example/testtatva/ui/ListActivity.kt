package com.example.testtatva.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testtatva.R
import com.example.testtatva.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        init()
    }

    private fun init() {
        mBinding.apply {

        }
    }
}