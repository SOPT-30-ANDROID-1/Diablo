package org.sopt.diablo.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.floor

class GridSpaceDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when(parent.getChildAdapterPosition(view) % 2) {
            0 -> outRect.right = ceil(margin / 2.0).toInt()
            1 -> outRect.left = floor(margin / 2.0).toInt()
        }
        outRect.bottom = margin
    }
}
