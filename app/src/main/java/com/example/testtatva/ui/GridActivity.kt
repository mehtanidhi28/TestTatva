package com.example.testtatva.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtatva.R
import com.example.testtatva.databinding.ActivityGridBinding
import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.random.Random

class GridActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityGridBinding
    private val mActivity by lazy {
        this@GridActivity
    }
    private var mAdapter: GridAdapter? = null
    private var clickedIndexCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid)
        init()
    }

    private fun init() {
        mBinding.apply {
            btnSubmit.setOnClickListener {
                if (edtValue.text.toString().trim().isEmpty()) {
                    Toast.makeText(mActivity, R.string.validation_text, Toast.LENGTH_SHORT).show()
                } else {
                    //calculate square root of input value
                    val enteredDigit = edtValue.text.toString().trim()
                    if (checkSquareRoot(enteredDigit.toInt())) {
                        //if square root is found then check value and create grid
                        val rootValue = sqrt(enteredDigit.toDouble())
                        Log.d("TAG:", "Value: $rootValue")
                        gridView.apply {
                            layoutManager = GridLayoutManager(mActivity, rootValue.toInt())
                            mAdapter = GridAdapter(rootValue.toInt()) { position ->
                                Log.d("TAG:", "POSITION: $position")
                                clickedIndexCount += 1
                                txtWon.visibility = if (clickedIndexCount == enteredDigit.toInt()) {
                                    View.VISIBLE
                                } else {
                                    View.GONE
                                }
                            }
                            adapter = mAdapter
                            val randomDelay = Random.nextLong(0, 10)
                            Log.d("TAG:", "DELAY: $randomDelay")
                            Handler(Looper.getMainLooper()).postDelayed({
                                mAdapter?.enableTile()
                            }, randomDelay)
                        }
                    } else {
                        Toast.makeText(mActivity, R.string.not_valid_number, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun checkSquareRoot(value: Int): Boolean {
        val squareRoot = sqrt(value.toDouble())
        val floor = floor(squareRoot)
        return squareRoot == floor
    }
}