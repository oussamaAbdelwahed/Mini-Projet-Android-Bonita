package tn.oussama.mp_android_bonita

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import kotlinx.coroutines.delay
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.ProcessContract
import tn.oussama.core.domain.ProcessInput
import tn.oussama.mp_android_bonita.framework.utils.InputType
import tn.oussama.mp_android_bonita.framework.utils.createUIWidgetFromType
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
               is DataState.Success<ProcessContract> -> {

                   spinnerRV.visibility = View.GONE
                   val processName:String = it.data.inputs[0].name
                   val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                       LinearLayout.LayoutParams.MATCH_PARENT)


                   val ll: LinearLayout = findViewById(R.id.formLinearLayout)
                   lp.setMargins(5, 11, 5, 15)
                   findViewById<TextView>(R.id.textView3).text=it.data.inputs[0].name
                   for( items : ProcessInput in it.data.inputs ){
                       items.inputs.forEachIndexed { index, i ->
                           val v  = createUIWidgetFromType(InputType.valueOf(i.type),this)
                           if(v != null){
                               if(index==0)  v.requestFocus()

                               val textV= TextView(this);
                               textV.text=i.name
                               v.tag =i.name
                               ll.addView(textV)
                               ll.addView(v)
                           }
                       }
                   }
                   val layoutInflater : LayoutInflater = LayoutInflater.from(this).context.getSystemService(
                       Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                   val btnSubmit: View = layoutInflater.inflate(R.layout.rounded_button_for_submit,null,false)
                   btnSubmit.layoutParams = lp
                   /*btnSubmit.background = ContextCompat.getDrawable(this, R.drawable.custom_login_button)
                   btnSubmit.minimumWidth = LinearLayout.LayoutParams.MATCH_PARENT*/
                   ll.addView(btnSubmit)
                   //hide spinner  here
                   Log.i("************************* FROM NETWORK GETTING PROCESS ************************* ","${it.data.inputs[0].name} ${it.data.inputs[0].type}")
               } else -> {
                 spinnerRV.visibility = View.GONE
               //show spinner  here and show error message
               }
           }
        })
        //show spinner  here
        spinnerRV.visibility = View.VISIBLE
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