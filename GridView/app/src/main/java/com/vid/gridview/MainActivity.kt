package com.vid.gridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var gridView: GridView
    var nameList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView)
        fillArray()
        var adapter = AnimalsAdapter(this@MainActivity, nameList, imageList)

        gridView.adapter = adapter
        
        gridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@MainActivity, "Your selected the ${nameList[position]}"
                ,Toast.LENGTH_LONG).show()
        }
    }

    fun fillArray(){
        nameList.add("Bird")
        nameList.add("Chicken")
        nameList.add("Dog")
        nameList.add("Fish")
        nameList.add("Monkey")
        nameList.add("Rabbit")
        nameList.add("Ship")
        nameList.add("Lion")

        imageList.add(R.drawable.u_1)
        imageList.add(R.drawable.u_2)
        imageList.add(R.drawable.u_3)
        imageList.add(R.drawable.u_4)
        imageList.add(R.drawable.u_5)
        imageList.add(R.drawable.u_6)
        imageList.add(R.drawable.u_7)
        imageList.add(R.drawable.u_8)

    }
}