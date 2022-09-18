package com.vid.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentExample: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("fragment", "Fragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("fragment", "Fragment onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("fragment", "Fragment onCreateView")

        return  inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("fragment", "Fragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()

        Log.i("fragment", "Fragment onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.i("fragment", "Fragment onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.i("fragment", "Fragment onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.i("fragment", "Fragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.i("fragment", "Fragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("fragment", "Fragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()

        Log.i("fragment", "Fragment onDetach")
    }
}
