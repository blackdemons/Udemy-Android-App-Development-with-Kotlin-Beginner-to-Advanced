package com.vid.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.vid.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding

    var itemList = ArrayList<String>()

    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = fileHelper.readDate(this)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)

        binding.lvItem.adapter = arrayAdapter

        binding.btnAdd.setOnClickListener {
            var itemName: String = binding.etToDoName.text.toString()
            itemList.add(itemName)
            binding.etToDoName.setText("")
            fileHelper.writeData(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()

        }
        binding.lvItem.setOnItemClickListener { parent, view, position, id ->
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete")
            alertDialog.setMessage("Do you want to delete this item from the list?")
            alertDialog.setCancelable(true)
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alertDialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemList, applicationContext)
            })

            alertDialog.create().show()

        }


    }
}