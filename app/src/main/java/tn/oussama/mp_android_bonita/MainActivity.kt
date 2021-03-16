package tn.oussama.mp_android_bonita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
           val intent = Intent(this, ListProcessesActivity::class.java).apply {
                putExtra("USER_CREDENTIALS", "NOTHING")
           }
           startActivity(intent)
        }
    }



}