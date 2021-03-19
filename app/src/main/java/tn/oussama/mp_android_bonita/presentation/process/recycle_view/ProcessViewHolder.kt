package tn.oussama.mp_android_bonita.presentation.process.recycle_view

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.process_recycler_view_item.view.*
import tn.oussama.core.domain.Process
import tn.oussama.mp_android_bonita.ListProcessesActivity
import tn.oussama.mp_android_bonita.ProcessActivity

class ProcessViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val label: TextView = itemView.label
    val type: TextView = itemView.type
    val createdOn: TextView = itemView.createdOn
    //add other fields here
    val btn: Button = itemView.btnShowMore

     fun bind(process: Process) {
        btn.setOnClickListener{
            val intent = Intent(itemView.context, ProcessActivity::class.java).apply {
                putExtra("PROCESS_ID", process.id)
            }
            itemView.context.startActivity(intent)
        }
        label.text = process.name
        type.text = process.version
        createdOn.text = process.deploymentDate.toString()
    }
}