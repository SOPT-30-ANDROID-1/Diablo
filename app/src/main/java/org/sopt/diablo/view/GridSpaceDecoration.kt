package org.sopt.diablo.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.diablo.util.dpToPx
import kotlin.math.ceil
import kotlin.math.floor

class GridSpaceDecoration(private val margin: Int, private val size: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (size) {
            2 -> {
                when (parent.getChildAdapterPosition(view) % size) {
                    0 -> outRect.right = ceil(margin / 2.0).toInt()
                    1 -> outRect.left = floor(margin / 2.0).toInt()
                }
            }
            3 -> {
                when (parent.getChildAdapterPosition(view) % size) {
                    0 -> outRect.right = ceil(margin / 3.0 * 2.0).toInt()
                    1 -> {
                        outRect.left = floor(margin / 3.0).toInt()
                        outRect.right = ceil(margin / 3.0).toInt()
                    }
                    2 -> outRect.left = floor(margin / 3.0 * 2.0).toInt()
                }
            }
        }
        outRect.bottom = margin
    }
}
