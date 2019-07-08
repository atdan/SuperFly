package com.example.superfly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.core.view.get
import com.example.superfly.R


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class MainActivity : AppCompatActivity(), SIgnUp.signL, OwnerOne.ownerOneL, OwnerTwo.ownerTwoL, UserOne.UserOneL, PilotOne.PilotOneL, PilotTwo.PilotTwoL {


    override fun clickPilotThree() {
        var i = Intent(this, PilotThree::class.java)
        startActivity(i)
    }

    override fun clickPilotTwo() {
        var fragment = PilotTwo()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }

    override fun clickUserTwo() {
        var i = Intent(this, UserTwo::class.java)
        startActivity(i)
    }


    override fun onNext() {
        changeUser()

    }




    override fun clickOwnerThree() {
        var i = Intent(this, OwnerThree::class.java)
        startActivity(i)
    }

    override fun clickOwnerTwo() {
        var fragment = OwnerTwo()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()

    }


    private fun changeUser(){

        when (register?.selectedItem.toString()) {
                    "User" -> user()
                    "Pilot" -> pilot()
                    "Owner" -> owner()
                }
//        register?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                var reg = parent?.getItemAtPosition(position).toString()
//                when (reg) {
//                    "User" -> user()
//                    "Pilot" -> pilot()
//                    "Owner" -> owner()
//                }
//            }
//
//        }


    }

    private fun owner(){
        var fragment = OwnerOne()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }

    private fun user(){
        var fragment = UserOne()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }

    private fun pilot(){
        var fragment = PilotOne()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }



//     fun validateForm(): Boolean {
//        var valid = true
//
//        val email = email.text.toString()
//        if (TextUtils.isEmpty(email)) {
//            email.error
//            valid = false
//        } else {
//            email.error = null
//        }
//
//        val password = password.text.toString()
//        val cpassword = confirmPassword.text.toString()
//        if (TextUtils.isEmpty(password)) {
//            password.error = "Required."
//            valid = false
//        } else if (password.)
//        else {
//            password.error = null
//        }
//
//        return valid
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment = SIgnUp()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack("SIgnUp").commit()

    }
}
