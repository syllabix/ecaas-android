package com.twomenandatruck.estimatorexample

import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ecaas.Ecaas
import ecaas.MoveType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //TODO: create example local and intl models

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
            //TODO: set selected move type for local
        }

        intlMoveButton.setOnClickListener {
            //TODO: set selected move type for international
        }
    }

    private fun updateEstimate() {
        //Integrate with Ecaas calculation service
    }


    //Implement the Ecaas Move Type
}
