package com.identify.datastorageapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.identify.datastorageapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Shared Preferences
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    var readUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences =
            this.getSharedPreferences("com.identify.datastorageapp", Context.MODE_PRIVATE)

        readUserName = sharedPreferences.getString("username", "")

        if (readUserName == "") {
            binding.textView.text = "Kaydedilen isim:"
        } else {
            binding.textView.text = "Kaydedilen isim: ${readUserName}"
        }
    }

    fun saveBtn(view: View) {

        val userName = binding.editText.text.toString()
        if (userName.trim() == "") {
            Toast.makeText(this@MainActivity, "Please enter name!", Toast.LENGTH_LONG).show()
        } else {
            sharedPreferences.edit().putString("username", userName).apply()
            binding.textView.text = "Kaydedilen isim: ${userName}"
        }


    }

    fun deleteBtn(view: View) {
        readUserName = sharedPreferences.getString("username", "")
        if (readUserName != "") {
            sharedPreferences.edit().remove("username").apply()
            binding.textView.text = "Kaydedilen isim:"
        }
    }
}