package com.capstone.mobileapp_capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.view.View
import com.capstone.mobileapp_capstone.databinding.ActivityProfileBinding
import android.widget.Toast

class Profile : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityProfileBinding
    private var m_bluetoothAdapter : BluetoothAdapter? = null
    private lateinit var m_pairedDevices: Set<BluetoothDevice>
    private val REQUEST_ENABLE_BLUETOOTH = 1

    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init buttons and clickables
        binding.docLink.setOnClickListener(this)
        binding.editProfile.setOnClickListener(this)

        binding.addDevice.setOnClickListener(this)

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(m_bluetoothAdapter == null) {
            Toast.makeText(baseContext, "Device does not support Bluetooth.",
                    Toast.LENGTH_SHORT).show()
            return
        }
        if(!m_bluetoothAdapter!!.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
        }

        //addDevice.setOnClickListener{ pairedDeviceList() }

    }
    override fun onClick(v: View){
        when (v.id) {
            R.id.docLink -> showDoc()
            R.id.editProfile -> editProfile()


            R.id.addDevice -> pairedDeviceList()
        }


    }

    private fun pairedDeviceList() {
        //TODO("Not yet implemented")
    }

    private fun editProfile() {
        //TODO("Not yet implemented")
    }

    private fun showDoc() {
        //TODO("Not yet implemented")
    }
}