package com.example.superfly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView

class BookingActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            var text = "You selected: "
            text += if (R.id.radioMale == checkedId) "male" else "female"
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }




    }



}
