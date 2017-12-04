package com.twomenandatruck.estimatorexample

import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ecaas.Ecaas
import ecaas.MoveType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val localMove = MoveTypeImpl("Local", "0.05", "0.06")
    private val intlMove = MoveTypeImpl("International", multiplier = "0.25", taxRate = "0.31")

    private var selectedMoveType: MoveType? = null
        set(value) {
            field = value
            when(value?.name) {
                "Local" -> {
                    localMoveButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(android.R.color.holo_blue_light, null))
                    intlMoveButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
                }
                "International" -> {
                    localMoveButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
                    intlMoveButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(android.R.color.holo_blue_light, null))
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        estimateTotalButton.setOnClickListener {
            updateEstimate()
        }

        localMoveButton.setOnClickListener {
            selectedMoveType = localMove

        }

        intlMoveButton.setOnClickListener {
            selectedMoveType = intlMove
        }
    }

    private fun updateEstimate() {
        val totalHours = estimatedHours.text.toString().toDouble()
        val rate = hourlyRate.text.toString()
        val date = moveDate.text.toString()
        val jobDetails = Ecaas.newJobDetails(totalHours, rate, date)
        val newTotal = Ecaas.calculateTotalCost(jobDetails, selectedMoveType)
        estimateTotal.text = "${newTotal.low} - ${newTotal.high}"
    }


    inner class MoveTypeImpl (
            private val name: String,
            private val multiplier: String,
            private val taxRate: String
    ): MoveType {
        override fun getName() = name
        override fun getMultiplier() = multiplier
        override fun getTaxRate() = taxRate
    }
}
