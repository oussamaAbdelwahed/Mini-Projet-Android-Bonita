package tn.oussama.mp_android_bonita

import tn.oussama.mp_android_bonita.presentation.process.recycle_view.RecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_process.*
import tn.oussama.mp_android_bonita.presentation.process.recycle_view.TopSpacingItemDecoration

class ListProcessesActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_process)

        initRecycleView()

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


   private fun initRecycleView() {
       recyclerView.apply {
           layoutManager = LinearLayoutManager(this@ListProcessesActivity)
           val topSpacingDecoration = TopSpacingItemDecoration(30)
           addItemDecoration(topSpacingDecoration)
           adapter = RecyclerViewAdapter()
       }
   }

}