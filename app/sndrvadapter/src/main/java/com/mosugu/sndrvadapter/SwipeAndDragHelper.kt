package com.mosugu.sndrvadapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class SwipeAndDragHelper(private val adapter : SwipeAndDragAdapter) : ItemTouchHelper.Callback(){
    private var dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
    var swipeFlags = ItemTouchHelper.LEFT

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {


        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                            target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemSwiped(viewHolder.adapterPosition)
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder?.itemView?.setBackgroundColor(Color.GRAY)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.setBackgroundColor(
            ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                    dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val trashIcon = ResourcesCompat.getDrawable(viewHolder.itemView.context.resources,
                                                            R.drawable.ic_baseline_delete_24, null)
        c.clipRect(viewHolder.itemView.right.toFloat() + dX, viewHolder.itemView.top.toFloat(),
            viewHolder.itemView.right.toFloat(), viewHolder.itemView.bottom.toFloat())

        if(viewHolder.itemView.right.toFloat() + dX > c.width * 2 / 3)
            c.drawColor(Color.GRAY)
        else
            c.drawColor(Color.RED)
        val textMargin = viewHolder.itemView.context.resources.getDimension(R.dimen.margin).roundToInt()
        if (trashIcon != null) {

            trashIcon.bounds  = Rect(
                viewHolder.itemView.right - 48 - textMargin,
                viewHolder.itemView.top + viewHolder.itemView.height / 2 - 24,
                viewHolder.itemView.right - textMargin,
                viewHolder.itemView.bottom - viewHolder.itemView.height / 2 + 24

//                viewHolder.itemView.right - (viewHolder.itemView.bottom - viewHolder.itemView.top) - textMargin,
//                     viewHolder.itemView.top,
//                viewHolder.itemView.right - textMargin,
//                     viewHolder.itemView.bottom
            )

            trashIcon.draw(c)
        }


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }

    fun setIsDragEnabled(enabled : Boolean) {
        if(!enabled) dragFlags = 0

    }

    fun setIsSwipeEnabled(enabled: Boolean) {
        if(!enabled) swipeFlags = 0

    }
}