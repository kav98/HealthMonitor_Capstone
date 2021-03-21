package com.capstone.mobileapp_capstone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.mobileapp_capstone.databinding.ActivityProfileBinding
import com.capstone.mobileapp_capstone.databinding.ActivitySensorHistoryBinding

class SensorHistory : AppCompatActivity(), View.OnClickListener{
    //Handles any database searching. for searching on the dashboard, parameter passing vs search in realtime

    private lateinit var binding: ActivitySensorHistoryBinding

    companion object{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensorHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onClick(v: View){
        when (v.id){

        }
    }
}