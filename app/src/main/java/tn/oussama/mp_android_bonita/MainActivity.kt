package tn.oussama.mp_android_bonita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    suspend fun test() {
        delay(1000*5)
        Log.i("BLOCKING_MAIN_THREAD","************")
    }







}