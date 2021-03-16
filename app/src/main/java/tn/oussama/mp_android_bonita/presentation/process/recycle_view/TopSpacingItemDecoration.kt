package tn.oussama.mp_android_bonita.presentation.process.recycle_view

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class TopSpacingItemDecoration(private val padding:Int) : RecyclerView.ItemDecoration(){
    //Applying some change to the rectangle of the ViewHolder
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = padding

    }
}