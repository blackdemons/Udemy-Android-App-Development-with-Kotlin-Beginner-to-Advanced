package com.vid.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    var contryNameList = ArrayList<String>()
    var detailList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    lateinit var adapter: CountriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        contryNameList.add("United Kingdom")
        contryNameList.add("Germany")
        contryNameList.add("USA")

        detailList.add("This is United Kingdom Flag")
        detailList.add("This is Germany Flag")
        detailList.add("This is USA Flag")

        imageList.add(R.drawable.u_1)
        imageList.add(R.drawable.u_2)
        imageList.add(R.drawable.u_3)

        adapter = CountriesAdapter(contryNameList, detailList, imageList, this@MainActivity)

        recyclerView.adapter = adapter


    }


}
