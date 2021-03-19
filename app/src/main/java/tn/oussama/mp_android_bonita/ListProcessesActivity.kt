package tn.oussama.mp_android_bonita

import android.content.SharedPreferences
import tn.oussama.mp_android_bonita.presentation.process.recycle_view.RecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.Process
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.utils.getUserFromSharedPreferences
import tn.oussama.mp_android_bonita.presentation.process.ListProcessStateEvent
import tn.oussama.mp_android_bonita.presentation.process.ListProcessesActivityViewModel
import tn.oussama.mp_android_bonita.presentation.process.recycle_view.TopSpacingItemDecoration
import tn.oussama.mp_android_bonita.presentation.user.LoginViewModel

@AndroidEntryPoint
class ListProcessesActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel : ListProcessesActivityViewModel by viewModels()

    /*companion object {
        lateinit var  preferences : SharedPreferences
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_process)

        //val u: User =  intent.getSerializableExtra("user") as User
        val u: User = getUserFromSharedPreferences("user")
        //preferences  = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);

        viewModel.getListOfProcesses().observe(this, Observer {
            when(it) {
                is DataState.Loading -> {
                    spinnerRV.visibility = View.VISIBLE
                }is DataState.Success -> {
                    spinnerRV.visibility = View.GONE
                    val l = it.data
                  if(l.size > 0) {
                    innerConstraintLayout.visibility = View.GONE
                    initRecyclerView(it.data)
                  }else{
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text="aucun processus n'est associé a vous pour le moment!"
                  }
                } is DataState.Error -> {
                    spinnerRV.visibility = View.GONE
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text="une erreur est survenue, ressayer plus tard!"
                }
            }
        })

        if(u.id > 0) {
          viewModel.setListProcessStateEvent(ListProcessStateEvent.GetProcessesEvent,userId = u.id,pageIndex = 0,perPage = 20)
        }


        //only these instructions is specific to the side menu work
        toggle  = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    //THESE METHOD ALSO
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
           return true
        }
        return super.onOptionsItemSelected(item)
    }


    //must take a list of process and forward it to RecyclerViewAdapter constructor
   private fun initRecyclerView(l: List<Process>) {
       recyclerView.apply {
           layoutManager = LinearLayoutManager(this@ListProcessesActivity)
           addItemDecoration(TopSpacingItemDecoration(30))
           //call this with the given parameter list
           adapter = RecyclerViewAdapter(l)
       }
   }

}