package com.vid.firebase

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.vid.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")

    val userList = ArrayList<Users>()
    val imageNameList = ArrayList<String>()
    lateinit var usersAdapter: UsersAdapter

    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storageReference: StorageReference = firebaseStorage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.fabButton.setOnClickListener {

            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = usersAdapter.getUserId(viewHolder.adapterPosition)

                myReference.child(id).removeValue()

                // delete()
                val imageName = usersAdapter.getImageName(viewHolder.adapterPosition)

                val imageReference = storageReference.child("images").child(imageName)

                imageReference.delete()

                Toast.makeText(applicationContext, "The user was deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }).attachToRecyclerView(binding.recycleView)
        retrieveDataFromDatabase()


    }

    fun retrieveDataFromDatabase() {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()

                for (eachUser in snapshot.children) {
                    val user = eachUser.getValue(Users::class.java)
                    if (user != null) {
                        print("userId: ${user.userId}")
                        print("userName: ${user.userName}")
                        print("userAge: ${user.userAge}")
                        print("userEmail: ${user.userEmail}")
                        print("****************************")

                        userList.add(user)
                    }

                    usersAdapter = UsersAdapter(this@MainActivity, userList)

                    binding.recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recycleView.adapter = usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) {
            showDialogMessage()
        } else if (item.itemId == R.id.singOut) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Delete All Users")
        dialogMessage.setMessage(
            "If click Yes, all users will be deleted," +
                    "If you want to delete a specific user, your can swipe the item you want to delete right or left"
        )
        dialogMessage.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

            myReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (eachUser in snapshot.children) {
                        val user = eachUser.getValue(Users::class.java)
                        if (user != null) {
                            imageNameList.add(user.imageName)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })

            val imageReference = storageReference.child("images")
            imageReference.delete()

            myReference.removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    for (imageName in imageNameList){
                        val imageReference = storageReference.child("image").child(imageName)
                        imageReference.delete()
                    }

                    usersAdapter.notifyDataSetChanged()

                    Toast.makeText(applicationContext, "All users were delete", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        dialogMessage.create().show()

    }
}