package com.capstone.mobileapp_capstone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.mobileapp_capstone.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity(), View.OnClickListener{
    //handles data display and dashboard controls
    private lateinit var binding: ActivityDashboardBinding

    companion object{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onClick(v: View){
        when (v.id){

        }
    }
}