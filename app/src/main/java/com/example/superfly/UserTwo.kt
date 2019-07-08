package com.example.superfly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.superfly.LandingActivity
import com.example.superfly.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.owner_three.*
import kotlinx.android.synthetic.main.owner_three.progress

class UserTwo : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sign_up -> toSignUp()
            R.id.terms -> signTerms()
        }
    }

    lateinit var mAuth: FirebaseAuth
    private var Wuser: String = "Welcome User"
    private var TAG = "com.example.superfly"


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
        setContentView(R.layout.owner_three)

        sign_up.setOnClickListener(this)
//        if(mAuth.currentUser != null){
            // Start signed in activity
//            startActivity(SignedInActivity.createIntent(this, null))
//            finish()
//        }
//        mAuth = FirebaseAuth.getInstance()



    }


    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
//            if (!validateForm()) {
//                return
//            }

        progress.visibility = View.VISIBLE

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
                progress.visibility = View.GONE
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]

    }

    private fun updateUI(user: FirebaseUser?){
        if (user != null){


        }
    }

    private fun toSignUp(){
        val i = Intent(this, LandingActivity::class.java)
        startActivity(i)
    }



    private fun signTerms(){
        val i = Intent(this, WelcomeActivity::class.java)
        startActivity(i)
    }
}
