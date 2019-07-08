package com.example.superfly


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superfly.R
import kotlinx.android.synthetic.main.owner_two.*
import kotlinx.android.synthetic.main.owner_two.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OwnerTwo : Fragment(), View.OnClickListener {

    private lateinit var listener: ownerTwoL

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.o_two -> listener.clickOwnerThree()
        }
    }

    interface ownerTwoL{
        fun clickOwnerThree()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ownerTwoL)
            listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.owner_two, container, false)
        v.o_two?.setOnClickListener(this)
        return v
        }
    }



