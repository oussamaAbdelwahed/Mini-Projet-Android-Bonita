package tn.oussama.mp_android_bonita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.presentation.process.ListProcessesActivityViewModel
import tn.oussama.mp_android_bonita.presentation.process.ProcessActivityViewModel

@AndroidEntryPoint
class ProcessActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel : ProcessActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        //here get processId from intent.Extra  -->
        val processID = intent.getLongExtra("PROCESS_ID",0L)

        viewModel.observableProcess().observe(this, Observer {
           when(it) {
               is DataState.Success<Process> -> {
                 Log.i("************************* FROM NETWORK GETTING PROCESS ************************* ","${it.data.id} ${it.data.name}")
               } else -> {
                  //show error message
               }
           }
        })

        viewModel.getProcessById(processID)

        toggle  = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}