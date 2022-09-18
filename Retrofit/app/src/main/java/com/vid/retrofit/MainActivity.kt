package com.vid.retrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.vid.retrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val baseURL = "https://jsonplaceholder.typicode.com/"

    var postList = ArrayList<Posts>()

    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.RecyclerView.layoutManager = LinearLayoutManager(this)


        showPosts()
    }

    fun showPosts() {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<List<Posts>> = retrofitAPI.getAllPost()

        call.enqueue(object : Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(response.isSuccessful){

                    binding.progressBar.isVisible = false
                    binding.RecyclerView.isVisible = true
                    postList = response.body() as ArrayList<Posts>

                    adapter = PostsAdapter(postList)

                    binding.RecyclerView.adapter = adapter

                }

            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(
                    applicationContext, t.localizedMessage, Toast.LENGTH_LONG
                ).show()

            }
        })
    }
}