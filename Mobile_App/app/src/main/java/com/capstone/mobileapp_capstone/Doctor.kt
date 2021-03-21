package com.capstone.mobileapp_capstone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.mobileapp_capstone.databinding.ActivityDoctorBinding


class Doctor : AppCompatActivity(), View.OnClickListener{
    //all API calls to the backend
    private lateinit var binding: ActivityDoctorBinding

    companion object{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onClick(v: View){
        when (v.id){

        }
    }
}