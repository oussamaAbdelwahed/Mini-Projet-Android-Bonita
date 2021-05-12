package tn.oussama.mp_android_bonita

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
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.ProcessContract
import tn.oussama.core.domain.ProcessInput
import tn.oussama.core.domain.ProcessInstanceSubmitResponse
import tn.oussama.mp_android_bonita.framework.utils.InputType
import tn.oussama.mp_android_bonita.framework.utils.createUIWidgetFromType
import tn.oussama.mp_android_bonita.framework.utils.getInputValue
import tn.oussama.mp_android_bonita.framework.utils.showToastMessage
import tn.oussama.mp_android_bonita.presentation.process.ProcessActivityViewModel

@AndroidEntryPoint
class ProcessActivity : AppCompatActivity() {

   private lateinit var toggle: ActionBarDrawerToggle
   private val viewModel : ProcessActivityViewModel by viewModels()
   private var processID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        this.processID = intent.getLongExtra("PROCESS_ID",0L)

        viewModel.observableProcess().observe(this, Observer { it ->
            when(it) {
               is DataState.Success<ProcessContract> -> {
                   spinnerRV.visibility = View.GONE
                   val processName:String = it.data.inputs[0].name
                   val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                       LinearLayout.LayoutParams.MATCH_PARENT)

                   val ll: LinearLayout = findViewById(R.id.formLinearLayout)
                   lp.setMargins(5, 11, 5, 15)
                   findViewById<TextView>(R.id.textView3).text=it.data.inputs[0].name
                   viewModel.processName = it.data.inputs[0].name
                 //fetching constraint and creating a list containing he names of required form fields
                  //THIS IS THE CODE BLOCK CAUSING THE FATAL EXCEPTION DUE TO SHARED REFERENCES LATEINIT IN MAIN ACT & LISTPROCESS CALL IT
                  it.data?.constraints?.forEach{ctr ->
                      Log.i("****** FOREACH LOOP CONSTRAINTS 1********",ctr.name)
                      val ctrRequiredFieldName = ctr?.expression?.split("?.")?.get(1)?.split("!=")?.get(0)
                      Log.i("****** FOREACH LOOP CONSTRAINTS 2********",ctr.name)
                       if(!ctrRequiredFieldName.isNullOrEmpty() && !ctrRequiredFieldName.isNullOrBlank() )
                          viewModel.requiredFields.add(ctrRequiredFieldName.trim())
                  }



                   for( items : ProcessInput in it.data.inputs ){
                       items.inputs.forEachIndexed { index, i ->
                           val v  = createUIWidgetFromType(InputType.valueOf(i.type),this)
                           if(v != null){
                               if(index==0)  v.requestFocus()

                               val textV= TextView(this);
                               if(viewModel.requiredFields.contains(i.name)){
                                   textV.text = HtmlCompat.fromHtml(
                                       "<span style='color:red;'><b> * </b></span> ${i.name}",
                                       HtmlCompat.FROM_HTML_MODE_LEGACY
                                   )
                               }else{
                                   textV.text = i.name
                               }
                               viewModel.inputNames.add(i.name)

                               v.tag = i.name
                               ll.addView(textV)
                               ll.addView(v)
                           }
                       }
                   }
                   val layoutInflater : LayoutInflater = LayoutInflater.from(this).context.getSystemService(
                       Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                   val btnSubmit: View = layoutInflater.inflate(R.layout.rounded_button_for_submit,null,false)
                   btnSubmit.layoutParams = lp

                   btnSubmit.setOnClickListener { e:View ->
                       this.onSubmitForm(e)
                   }

                   ll.addView(btnSubmit)
               } else -> {
                 spinnerRV.visibility = View.GONE
                 showToastMessage(this,"Une erreur est survenue, veuillez ressayer")
               }
           }
        })

        viewModel.observableProcessInstanceSubmitResponse().observe(this, Observer { it ->
            when (it) {
              is DataState.Success<ProcessInstanceSubmitResponse> -> {
                  spinnerRV.visibility = View.GONE
                  showToastMessage(this,"l'instance de processus a été envoyé avec succés, l'id de l'instance est ${it.data.caseId.toString()}")
              } else -> {
                spinnerRV.visibility = View.GONE
                showToastMessage(this,"Une erreur est survenue, veuillez ressayer")
               }
            }
        })

        spinnerRV.visibility = View.VISIBLE
        viewModel.getProcessById(this.processID)

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

    fun onSubmitForm(v: View) {
        val ll: LinearLayout = findViewById(R.id.formLinearLayout)
        var  v:View? = null
        var inputVal: Any? = null
        val data : HashMap<String,Any> = HashMap()
        var j = 0
        var i = 0
        var canSubmit = true
        while(i < viewModel.inputNames.size && canSubmit){
          v = ll.findViewWithTag(viewModel.inputNames[i])
          inputVal = getInputValue(v)
          if(j < viewModel.requiredFields.size) {
            if(inputVal.toString().isNullOrBlank() || inputVal.toString().isNullOrEmpty()){
              canSubmit = false
              showToastMessage(this,"le champ ${viewModel.requiredFields[j]} est obligatoire, veuillez le remplir!")
            }else{
              data[viewModel.inputNames[i]] = inputVal
            }
          }else{
              data[viewModel.inputNames[i]] = inputVal
          }
          i++
          j++
        }

        if(canSubmit){
            val hm: HashMap<String,HashMap<String,Any>> = HashMap()
            hm.put(viewModel.processName, data)

            spinnerRV.visibility = View.VISIBLE
            viewModel.postProcessInstance(this.processID,hm)
        }
    }
}