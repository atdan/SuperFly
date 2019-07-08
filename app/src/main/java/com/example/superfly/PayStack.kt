package com.example.superfly

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.superfly.R
import kotlinx.android.synthetic.main.activity_pay_stack.*
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.card.user_card_number
import kotlinx.android.synthetic.main.card.user_expiry_month
import kotlinx.android.synthetic.main.card.user_expiry_year
import kotlinx.android.synthetic.main.user_two.*
import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

class PayStack : AppCompatActivity() {

    private val your_api_key = "pk_test_4159c31dbc813e1c007668e8f9edf765e4306dcc"

    private//check whether the email field is empty
    //check whether the card-number field is empty
    //check whether the expiry-month field is empty
    //check whether the expiry-year field is empty
    //check whether the card-cvv field is empty
    val isUserEntryValid: Boolean
        get() {
            var isValid = true
            when {
                TextUtils.isEmpty(user_card_number.text.toString()) -> {
                    user_card_number?.error = "This field is required"
                    isValid = false
                }
                TextUtils.isEmpty(user_expiry_month.text.toString()) -> {
                    user_expiry_month?.error = "This field is required"
                    isValid = false
                }
                TextUtils.isEmpty(user_expiry_year.text.toString()) -> {
                    user_expiry_year?.error = "This field is required"
                    isValid = false
                }
                TextUtils.isEmpty(usercvv.getText().toString()) -> {
                    usercvv?.error = "This field is required"
                    isValid = false
                }
            }
            return isValid
        }
    private val isInternetAvailable: Boolean
        get() {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.getActiveNetworkInfo()
            return activeNetworkInfo != null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_stack)
        //This following line is very important, you must initialize the Paystack SDK before using any Paystack class or interface.
        PaystackSdk.initialize(this.applicationContext)
        //Also, set your public key (from step 1 above) like this:
        PaystackSdk.setPublicKey(your_api_key)
        //initialize views

        //handle the onClick when the 'pay-button' is pressed
        payButton.setOnClickListener {
            //check whether user filled all the fields and there is internet connection
            if (isUserEntryValid && isInternetAvailable) {
                prepareToChargeUser(2000 * 100) //In this case, we're charging the user #2000
            } else {
                // Notify the user to check his internet connection or validate his entries
            }
        }
    }

    private fun prepareToChargeUser(amountToBeCharged: Int) {
        //get all the EditText entries as String and int values for convenience
        val email = email_a?.text.toString().trim()
        val cardNumber = user_card_number?.text.toString().trim()
        val expiryMonth = Integer.parseInt(user_expiry_month?.text.toString().trim())
        val expiryYear = Integer.parseInt(user_expiry_year?.text.toString().trim())
        val cvv = usercvv?.text.toString().trim()
        /*
     For testing purposes, Paystack provides a test card.. If you're still in the testing stage of your project,
     consider using the test cards, they are awesome!
     String cardNumber = "4084084084084081";
     int expiryMonth = 11; //any month in the future
     int expiryYear = 18; // any year in the future. '2018' would work also!
     String cvv = "408"; // cvv of the test card
     */
        //Create a new Card object based on the card details
        val card = Card(cardNumber, expiryMonth, expiryYear, cvv)
        //check whether the card is valid like this:
        if (!(card.isValid)) {
            Toast.makeText(applicationContext, "Card is not Valid", Toast.LENGTH_LONG).show()
            return
        }
        //Create a new Charge object
        val charge = Charge()
        charge.card = card
        charge.email = email
        charge.amount = amountToBeCharged
        //proceed to charge the user
        chargeTheUser(charge)
    }

    private fun chargeTheUser(charge: Charge) {
        PaystackSdk.chargeCard(this, charge, object : Paystack.TransactionCallback {
            override fun beforeValidate(transaction: Transaction) {
                //You can set a progress bar or progress dialog so that the user knows something is going on
            }

            override fun onSuccess(transaction: Transaction) {
                Toast.makeText(applicationContext, "Payment successful!!!", Toast.LENGTH_LONG).show()
                //Payment successful; proceed to give your user the product/service they paid for.
            }

            override fun onError(error: Throwable, transaction: Transaction) {
                AlertDialog.Builder(applicationContext)
                    .setMessage("Payment unsuccessful, please try again later")
                    .setCancelable(false)
                    .setPositiveButton("Okay") { _, _ ->
                        //try again
                    }
                    .show()
            }
        })
    }
}