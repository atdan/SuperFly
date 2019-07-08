package com.example.superfly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView
import kotlinx.android.synthetic.main.activity_booking.*

class BookingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.next -> Intent()
        }
    }

    fun Intent(){
        var i= Intent(this, PayStack::class.java)
        startActivity(i)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        next?.setOnClickListener(this)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            var text = "You selected: "
            text += if (R.id.radioMale == checkedId) "Book a Seat" else "Book a Flight"
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }




    }



}
