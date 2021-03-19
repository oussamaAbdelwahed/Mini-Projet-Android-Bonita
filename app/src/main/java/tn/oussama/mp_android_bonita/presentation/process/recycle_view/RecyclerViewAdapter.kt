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

class RecyclerViewAdapter(val dataSet: List<Process> = listOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return ProcessViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.process_recycler_view_item,parent,false)
      )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder) {
           is ProcessViewHolder -> holder.bind(this.dataSet[position])
       }
    }

    override fun getItemCount(): Int {
       return this.dataSet.count()
    }

}