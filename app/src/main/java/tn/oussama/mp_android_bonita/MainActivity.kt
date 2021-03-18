package tn.oussama.mp_android_bonita

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import tn.oussama.mp_android_bonita.framework.network.retrofit.ProcessRetrofit
import tn.oussama.mp_android_bonita.presentation.user.LoginViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()

    companion object {
      lateinit var  preferences : SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        preferences  = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.isLoggedIn().observe(this, Observer {
            //here end spinner/ProgressBar animation
            if(!it) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = viewModel.errorMsg
            }else{
                errorMessage.visibility = View.INVISIBLE
                errorMessage.text = ""
                val intent = Intent(this, ListProcessesActivity::class.java).apply {
                    putExtra("USER_CREDENTIALS", "NOTHING")
                }
                startActivity(intent)
            }
        })

        btnLogin.setOnClickListener {
            //here begin spinner/ProgressBar animation
            viewModel.login(username = username.text.toString(),password = password.text.toString())

           /*
           val intent = Intent(this, ListProcessesActivity::class.java).apply {
                putExtra("USER_CREDENTIALS", "NOTHING")
           }
           startActivity(intent)*/
        }
    }



}