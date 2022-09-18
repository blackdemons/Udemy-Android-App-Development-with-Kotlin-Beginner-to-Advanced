package com.vid.listfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.vid.listfragment.databinding.FragmentMyListBinding


class MyListFragment : ListFragment() {

    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter =
            activity?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.cities,
                    android.R.layout.simple_list_item_1
                )
            }
        listAdapter = arrayAdapter
        binding.list.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(activity, SecondActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
