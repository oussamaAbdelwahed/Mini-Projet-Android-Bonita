package tn.oussama.mp_android_bonita


import android.content.Context
import android.graphics.drawable.Drawable
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
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list_process.*
import kotlinx.android.synthetic.main.activity_main.view.*
import tn.oussama.core.data.DataState
import tn.oussama.core.domain.Category
import tn.oussama.core.domain.Process
import tn.oussama.core.domain.User
import tn.oussama.mp_android_bonita.framework.utils.getUserFromSharedPreferences
import tn.oussama.mp_android_bonita.presentation.process.ListProcessStateEvent
import tn.oussama.mp_android_bonita.presentation.process.ListProcessesActivityViewModel
import tn.oussama.mp_android_bonita.presentation.process.recycle_view.RecyclerViewAdapter
import tn.oussama.mp_android_bonita.presentation.process.recycle_view.TopSpacingItemDecoration
import kotlin.properties.Delegates


@AndroidEntryPoint
class ListProcessesActivity : AppCompatActivity() {
    private var networkCall1Done = false
    private var networkCall2Done = false
    private lateinit var toggle: ActionBarDrawerToggle
    private val viewModel : ListProcessesActivityViewModel by viewModels()
    lateinit  private var categContainer: LinearLayout
    lateinit private var previousSelectedCat: ConstraintLayout
    private var userID by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_process)
        this.categContainer = findViewById(R.id.categoriesInnerContainer)

        val u: User = getUserFromSharedPreferences("user")
        userID = u.id


        viewModel.getListOfProcesses().observe(this, Observer {
            when(it) {
                is DataState.Loading -> {
                    spinnerRV.visibility = View.VISIBLE
                }is DataState.Success -> {
                  this.networkCall1Done = true
                  if(this.networkCall2Done) {
                     spinnerRV.visibility = View.GONE
                  }
                  val l = it.data
                  if(l.isNotEmpty()) {
                    innerConstraintLayout.visibility = View.GONE
                    initRecyclerView(it.data)
                  }else{
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text="aucun processus n'est associé a vous pour le moment!"
                  }
                } is DataState.Error -> {
                    this.networkCall1Done = true
                   if(this.networkCall2Done) {
                      spinnerRV.visibility = View.GONE
                   }
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text="une erreur est survenue, ressayer plus tard!"
                }
            }
        })

        viewModel.getListOfCategories().observe(this, Observer {
            when(it) {
                is DataState.Loading -> {
                    spinnerRV.visibility = View.VISIBLE
                }is DataState.Success -> {
                  this.networkCall2Done = true
                  if(this.networkCall1Done) {
                    spinnerRV.visibility = View.GONE
                  }
                  val l = it.data
                  if(l.isNotEmpty()) {
                      innerConstraintLayout.visibility = View.GONE
                      val ca: Category = Category(0,"Toutes Les Catégories","Tous" ,null,"")
                      val res = l.toMutableList()
                      res.add(0,ca)
                      for(c: Category in res) {
                          this.addCategoryUIWidget(c)
                      }

                    //here must dynamically fill up the horizontal view with this list of categories
                  }
                } is DataState.Error -> {
                   this.networkCall2Done = true
                   if(this.networkCall1Done) {
                     spinnerRV.visibility = View.GONE
                   }
                   errorMsg.visibility = View.VISIBLE
                   errorMsg.text="une erreur est survenue, veuillez ressayer plus tard!"
                }
            }
        })

        if(u.id > 0) {
          viewModel.setListProcessStateEvent(ListProcessStateEvent.GetCategoriesEvent,userId = u.id)
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
           adapter = RecyclerViewAdapter(l)
       }
   }

    private fun addCategoryUIWidget(category: Category) {
        val layoutInflater : LayoutInflater = LayoutInflater.from(this).context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val v: View = layoutInflater.inflate(R.layout.rounded_button,null,false)
        val cardView: CardView  = v.findViewById(R.id.btnLogin)
        val textView: TextView  =v.findViewById(R.id.categName)
        //textView.id = StrictMath.toIntExact(category.id)
        textView.text = category.displayName
        //cardView.id= StrictMath.toIntExact(category.id)
        cardView.tag=category.id.toString()



        if(category.id == 0L) {
          this.previousSelectedCat = v.findViewById(R.id.bgConstraintLayout) as ConstraintLayout
          this.previousSelectedCat.background = ContextCompat.getDrawable(this, R.drawable.custom_login_button)
        }

        v.setOnClickListener {
            this.onCategoryClicked(it)
        }
        this.categContainer.addView(v)
    }

    private fun onCategoryClicked(btn: View) {
        //here we must do twiking to show all the spinner container when changing to a new category
        //this instruction is added now
        innerConstraintLayout.visibility = View.VISIBLE
        //end added instruction
        spinnerRV.visibility = View.VISIBLE
        this.previousSelectedCat.background = ContextCompat.getDrawable(this, R.drawable.custom_category_button)
        this.previousSelectedCat = btn.findViewById(R.id.bgConstraintLayout) as ConstraintLayout
        this.previousSelectedCat.background = ContextCompat.getDrawable(this, R.drawable.custom_login_button)
        val cardView: CardView  = btn.findViewById(R.id.btnLogin)

        val catID: Long =cardView.tag.toString().toLong()
        if(catID != 0L)
          viewModel.setListProcessStateEvent(ListProcessStateEvent.GetProcessesByCategoryEvent,userId = userID,pageIndex = 0,perPage = 20,categID = catID)
        else
          viewModel.setListProcessStateEvent(ListProcessStateEvent.GetProcessesEvent,userId = userID,pageIndex = 0,perPage = 20)

    }

}