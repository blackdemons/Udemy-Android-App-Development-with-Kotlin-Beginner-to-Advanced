package com.vid.firebase

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.vid.firebase.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateUserBinding

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUri: Uri? = null

    val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storageReference: StorageReference = firebaseStorage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Update User"
        //register
        registerActivityResult()

        getAndSendData()

        binding.btnUpdateUser.setOnClickListener {
            uploadPhoto()
        }

        binding.userUpdateProfileImage.setOnClickListener {
            chooseImage()
        }
    }


    fun chooseImage() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            activityResultLauncher.launch(intent)
        }
    }

    fun registerActivityResult() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val imageData = result.data

                if (resultCode == RESULT_OK && imageData != null) {
                    imageUri = imageData.data

                    //Picasso
                    imageUri?.let {
                        Picasso.get().load(it).into(binding.userUpdateProfileImage)
                    }
                }
            })
    }


    fun getAndSendData() {

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")
        val imageUrl = intent.getStringExtra("imageUrl").toString()

        binding.etUpdateName.setText(name)
        binding.etUpdateAge.setText(age)
        binding.etUpdateEmail.setText(email)
        Picasso.get().load(imageUrl).into(binding.userUpdateProfileImage)
    }

    fun updateData(imageURL: String, imageName: String) {
        val updateName = binding.etUpdateName.text.toString()
        val updateAge = binding.etUpdateAge.text.toString()
        val updateEmail = binding.etUpdateEmail.text.toString()
        val userId = intent.getStringExtra("id").toString()


        val userMap = mutableMapOf<String, Any>()
        userMap["userId"] = userId
        userMap["userName"] = updateName
        userMap["userAge"] = updateAge
        userMap["userEmail"] = updateEmail
        userMap["url"] = imageURL
        userMap["imageName"] = imageName

        myReference.child("id").updateChildren(userMap).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "The user has been update", Toast.LENGTH_SHORT)
                    .show()

                binding.btnUpdateUser.isClickable = true
                binding.progressBarUpdateUser.visibility = View.INVISIBLE

                finish()
            }
        }
    }

    fun uploadPhoto() {
        binding.btnUpdateUser.isClickable = false
        binding.progressBarUpdateUser.visibility = View.VISIBLE

        //UUID
        val imageName = intent.getStringExtra("imageName").toString()
        val imageReference = storageReference.child("images").child(imageName)

        imageUri?.let { uri ->
            imageReference.putFile(uri).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image updated", Toast.LENGTH_SHORT).show()

                //downloadable uri

                val myUploadedImageReference = storageReference.child("images").child(imageName)

                myUploadedImageReference.downloadUrl.addOnSuccessListener { url ->
                    val imageURL = url.toString()

                    updateData(imageURL, imageName)

                }

            }.addOnFailureListener {
                Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }
}
