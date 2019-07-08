package com.example.superfly


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superfly.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SIgnUp : Fragment(), View.OnClickListener {

    lateinit var listener: signL

    override fun onClick(v: View?) {
        //To change body of created functions use File | Settings | File Templates.
        when(v?.id){
            R.id.sign_inn -> signInto()
            R.id.next_s -> listener.onNext()
        }

    }

    interface signL{
        fun onNext()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is signL)
            listener = context
    }


    private fun signInto(){
        val i = Intent(activity, WelcomeActivity::class.java)
        startActivity(i)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_sign_up, container, false)
        v.sign_inn?.setOnClickListener(this)
        v.next_s?.setOnClickListener(this)
        return v
    }


}
