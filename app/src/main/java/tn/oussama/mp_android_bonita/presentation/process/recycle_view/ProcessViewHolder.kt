package tn.oussama.mp_android_bonita.presentation.process.recycle_view

import android.R.id
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.fromHtml
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.process_recycler_view_item.view.*
import tn.oussama.core.domain.Process
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
         val processLabel = "<p><span style='color:#C0392B;'><b>PROCESSUS: </b></span> ${process.name}</p>"
         val processType = "<p><span style='color:#C0392B;'><b>TYPE: </b></span> ${process.version}</p>"
         val processCreationDate = "<p><span style='color:#C0392B;'><b>CRÉE LE: </b></span> ${process.deploymentDate.toString()}</p>"
         label.text = fromHtml(processLabel, HtmlCompat.FROM_HTML_MODE_LEGACY)
         type.text = fromHtml(processType,HtmlCompat.FROM_HTML_MODE_LEGACY)
         createdOn.text = fromHtml(processCreationDate,HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}