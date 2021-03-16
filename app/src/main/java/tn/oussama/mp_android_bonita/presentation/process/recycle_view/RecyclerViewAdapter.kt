package tn.oussama.mp_android_bonita.presentation.process.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.process_recycler_view_item.view.*
import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.R

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet : List<Process>  = listOf(
        Process("Process 1","ADMIN","1 day ago"),
        Process("Process 2","EXTERN","2 day ago"),
        Process("Process 3","USERS","6 day ago"),
        Process("Process 4","ADMIN","1 day ago"),
        Process("Process 5","USERS","3 day ago"),
        Process("Process 6","ADMIN","2day ago"),
        Process("Process 7","EXTERN","1 day ago"),
        Process("Process 8","ADMIN","1 day ago"),
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