package com.capstone.mobileapp_capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
        m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
        val list : ArrayList<BluetoothDevice> = ArrayList()

        if (!m_pairedDevices.isEmpty()) {
            for (device: BluetoothDevice in m_pairedDevices) {
                list.add(device)
                Log.i("device", ""+device)
            }
        } else {
            Toast.makeText(baseContext,("no paired bluetooth devices found"),Toast.LENGTH_SHORT).show()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.deviceList.adapter = adapter
        binding.deviceList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val device: BluetoothDevice = list[position]
            val address: String = device.address

            val intent = Intent(this, SensorHistory::class.java)
            intent.putExtra(EXTRA_ADDRESS, address)
            startActivity(intent)
        }
    }

    private fun editProfile() {
        //TODO("Not yet implemented")
    }

    private fun showDoc() {
        //TODO("Not yet implemented")
    }
}