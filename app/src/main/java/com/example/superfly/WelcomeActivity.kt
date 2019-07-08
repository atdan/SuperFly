package com.example.superfly

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.superfly.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.activity_welcome.progress
import kotlinx.android.synthetic.main.fragment_sign_up.*

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        //To change body of created functions use File | Settings | File Templates.
        when (v?.id){
            R.id.sign_in -> signIn(email_a.text.toString(), password.text.toString())
        }
    }
    private var TAG = "com.example.superfly"
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
//        if (!validateForm()) {
//            return
//        }

        progress.visibility = View.VISIBLE

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
//                if (!task.isSuccessful) {
//                    status.setText(R.string.auth_failed)
//                }
                progress.visibility = View.GONE
                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

    @SuppressLint("SetTextI18n")
    fun updateUI(user: FirebaseUser?){
        if (user != null){

        }
    }

//    private fun toSignIn(){
//        signIn(email.text.toString(), passwords.text.toString())
//        val i = Intent(this, Owner::class.java)
//        startActivity(i)
//    }
}
