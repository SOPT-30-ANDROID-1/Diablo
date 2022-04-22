package org.sopt.diablo.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when(parent.getChildAdapterPosition(view) % 2) {
            0 -> {
                outRect.right = Math.ceil(margin.toDouble() / 2).toInt()
            }
            1 -> {
                outRect.left = Math.floor(margin.toDouble() / 2).toInt()
            }
        }
        outRect.bottom = margin
    }
}
