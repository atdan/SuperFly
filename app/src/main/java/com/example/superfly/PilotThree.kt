package com.example.superfly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.superfly.LandingOwnersActivity
import com.example.superfly.LandingPilotActivity
import com.example.superfly.R
import kotlinx.android.synthetic.main.activity_pilot_three.*

class PilotThree : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.sign_p -> toSignUp()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilot_three)

        sign_p?.setOnClickListener(this)
    }

    private fun toSignUp(){
        val i = Intent(this, LandingPilotActivity::class.java)
        startActivity(i)
    }
}
