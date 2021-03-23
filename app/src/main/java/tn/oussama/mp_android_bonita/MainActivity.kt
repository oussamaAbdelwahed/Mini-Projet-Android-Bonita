package tn.oussama.mp_android_bonita

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import tn.oussama.mp_android_bonita.framework.utils.clearUserSession
import tn.oussama.mp_android_bonita.framework.utils.storeToSharedPreferences
import tn.oussama.mp_android_bonita.presentation.user.LoginViewModel
import java.io.Serializable
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()

    companion object {
      lateinit var  preferences : SharedPreferences
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences  = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);

        if(intent.getBooleanExtra("LOGOUT",false)) {
            viewModel.logout()
        }


        viewModel.isLoggedIn().observe(this, Observer {
            this.lifecycleScope.launch {
                delay(1000)
                spinner.visibility = View.INVISIBLE
            }
            if(it=="FALSE") {
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = viewModel.errorMsg
            }else if(it=="TRUE"){
                errorMessage.visibility = View.INVISIBLE
                val intent = Intent(this, ListProcessesActivity::class.java).apply {
                    putExtra("user",viewModel.user)
                    storeToSharedPreferences("user",viewModel.user as Serializable)
                }

                startActivity(intent)
            }
        })

        btnLogin.setOnClickListener {
            spinner.visibility = View.VISIBLE
            viewModel.login(username = username.text.toString(),password = password.text.toString())
        }
    }

}