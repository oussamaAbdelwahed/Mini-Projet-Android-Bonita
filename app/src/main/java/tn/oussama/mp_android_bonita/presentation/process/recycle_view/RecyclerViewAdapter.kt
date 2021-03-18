package tn.oussama.mp_android_bonita.presentation.process.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.process_recycler_view_item.view.*
import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.R
import java.util.*

/*
*   var id: Long=0,
      var icon: String="",
      var deploymentDate: Date,
      var description: String="",
      var activationState: String="",
      var name: String="",
      var deployedBy: Int=0,
      var actorInitiatorId: Long=0,
      var lastUpdateDate: Date?=null,
      var configurationState: String="",
      var version: String="",
* */

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet : List<Process>  = listOf(
        Process(id=1,version = "ADMIN",name="1 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=2,version="EXTERN",name="2 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=3,version="USERS",name="6 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=4,version="ADMIN",name="1 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=5,version="USERS",name="3 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=6,version="ADMIN",name="2day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=7,version="EXTERN",name="1 day ago",deploymentDate=  Date(System.currentTimeMillis())),
        Process(id=8,version="ADMIN",name="1 day ago",deploymentDate=  Date(System.currentTimeMillis()))
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return ProcessViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.process_recycler_view_item,parent,false)
      )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder) {
           is ProcessViewHolder -> holder.bind(dataSet[position])
       }
    }

    override fun getItemCount(): Int {
       return dataSet.count()
    }



}