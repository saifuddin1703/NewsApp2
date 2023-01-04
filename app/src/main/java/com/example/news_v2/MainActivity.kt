package com.example.news_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.*
import com.example.news_v2.databinding.ActivityMainBinding
import com.example.news_v2.ui.home.HomeViewModel
import com.example.news_v2.utils.Status
import com.example.news_v2.utils.TAG

class MainActivity : AppCompatActivity() {
//    lateinit var homeViewModel : HomeViewModel
    lateinit var binding : ActivityMainBinding
    val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        homeViewModel.getTopHeadlines().observe(this){result ->
//            when (result.status){
//
//                Status.LOADING ->{
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.message.visibility = View.GONE
//                }
//
//                Status.SUCCESS ->{
//                    binding.progressBar.visibility = View.GONE
//                    binding.message.visibility = View.VISIBLE
//                    binding.message.text = "Api called"
//                }
//
//                Status.ERROR -> {
//                    binding.progressBar.visibility = View.GONE
//                    binding.message.visibility = View.VISIBLE
//                    binding.message.text = "Api calling fail"
//                }
//            }
//        }
    }
}