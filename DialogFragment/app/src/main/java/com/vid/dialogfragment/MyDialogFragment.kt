package com.vid.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vid.dialogfragment.databinding.FragmentMyDialogBinding


class MyDialogFragment : DialogFragment() {
    private var _binding: FragmentMyDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyDialogBinding.inflate(inflater, container, false)

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        binding.btnDialogOK.setOnClickListener {
            val userName: String = binding.etDialogName.text.toString()
            val userAge: Int = binding.etDialogAge.text.toString().toInt()

            val mainActivity: MainActivity = activity as MainActivity
            mainActivity.getUserData(userName, userAge)

            dialog!!.dismiss()
        }
        binding.btnDialogCancel.setOnClickListener {
            dialog!!.dismiss()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}