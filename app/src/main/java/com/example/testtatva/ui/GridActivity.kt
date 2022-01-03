package com.example.testtatva.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
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
    private var randomIndexList = ArrayList<Int>()
    private var randomIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid)
        init()
    }

    private fun init() {
        mBinding.apply {
            btnSubmit.setOnClickListener {
                edtValue.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        clickedIndexCount = 0
                        randomIndexList.clear()
                        txtWon.visibility = View.GONE
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }
                })
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
                                if (clickedIndexCount == enteredDigit.toInt()) {
                                    txtWon.visibility = View.VISIBLE
                                } else {
                                    txtWon.visibility = View.GONE
                                    mAdapter?.enableTile(getNextRandom())
                                }
                            }
                            adapter = mAdapter
                            randomIndex = Random.nextInt(0, enteredDigit.toInt())
                            Log.d("TAG:", "randomIndex: $randomIndex")
                            Handler(Looper.getMainLooper()).postDelayed({
                                randomIndexList.add(randomIndex)
                                mAdapter?.enableTile(randomIndex)
                            }, 3000)
                        }
                    } else {
                        Toast.makeText(mActivity, R.string.not_valid_number, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun getNextRandom(): Int {
        val nextRandom = Random.nextInt(0, mBinding.edtValue.text.toString().trim().toInt())
        randomIndexList.any {
            it == nextRandom
        }.let {
            if (it) {
                getNextRandom()
            } else {
                randomIndexList.add(nextRandom)
            }
            return@let
        }
        return nextRandom
    }

    private fun checkSquareRoot(value: Int): Boolean {
        val squareRoot = sqrt(value.toDouble())
        val floor = floor(squareRoot)
        return squareRoot == floor
    }
}