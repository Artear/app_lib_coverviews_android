package com.artear.stevedoreviewsexample

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.artear.stevedore.articleitem.presentation.ArticleItemData
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreitems.repository.model.box.BoxStyle
import com.artear.stevedore.stevedoreviews.presentation.adapter.StevedoreAdapter
import com.artear.tools.android.log.logD

class GridSpacingItemDecoration : RecyclerView.ItemDecoration() {


    var itemDetailsMap = hashMapOf<Int, Detail>()
    var drawable: Drawable? = null

    private fun resetRect(outRect: Rect) {
        outRect.bottom = 0
        outRect.top = 0
        outRect.left = 0
        outRect.right = 0
    }


    class Detail(
            var top: Int = 0,
            var right: Int = 0,
            var bottom: Int = 0,
            var left: Int = 0,
            var color: String = "") {


        override fun toString(): String {
            return " color: " + color +
                    " left: " + left +
                    " right: " + right +
                    " left: " + left +
                    " bottom: " + bottom +
                    " top: " + top

        }
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        resetRect(outRect)
        val position = parent.getChildAdapterPosition(view)
        val detail = getItemDecorations(parent, position)
        outRect.set(detail.left, detail.top, detail.right, detail.bottom)
    }


    private fun getItemDecorations(parent: RecyclerView, position: Int): Detail {
        Log.e("decoration", "position" + position + " found: " + (itemDetailsMap[position] != null))
        if (itemDetailsMap[position] == null) {
            itemDetailsMap[position] = setItemDecorations(parent, position)
        }
        return itemDetailsMap[position]!!
    }


    private fun setItemDecorations(parent: RecyclerView, position: Int): Detail {
        val item: ArtearItem = (parent.adapter as StevedoreAdapter).list[position]

        val detail = Detail(
                item.artearItemDecoration.marginTop,
                item.artearItemDecoration.marginRight,
                item.artearItemDecoration.marginBottom,
                item.artearItemDecoration.marginLeft
        )

        item.artearItemDecoration.backgroundColor?.let {
            detail.color = it
        }

        Log.e("decoration", "detail" + detail)

        return detail
    }

    private fun getBoxStyle(nextItem: ArtearItem): BoxStyle? {
        if (nextItem.model is ArticleItemData<*>) {
            val articleData = nextItem.model as ArticleItemData<*>
            if (articleData.style is BoxStyle) {
                return articleData.style as BoxStyle
            }
        }
        return null
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        drawborder(c, parent)
    }

    private fun drawborder(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            itemDetailsMap[position]?.let { decoration ->
                val backgroundColor = getColor(decoration.color, parent.context, R.color.bg_fail)

                val marginList: ArrayList<Rect> = ArrayList()

// LEFT
                marginList.add(Rect(
                        (child.x - decoration.left).toInt(),
                        (child.y).toInt(),
                        child.left,
                        child.bottom
                ))


// BOTTOM
                marginList.add(Rect(
                        (child.x - decoration.left).toInt(),
                        child.height + child.y.toInt(),
                        child.right + decoration.right,
                        child.bottom + decoration.bottom
                ))

// TOP
                marginList.add(Rect(
                        (child.x - decoration.left).toInt(),
                        child.top - decoration.top,
                        child.right + decoration.right,
                        child.y.toInt()
                ))

// RIGHT
                marginList.add(Rect(
                        child.right,
                        (child.y).toInt(),
                        child.right + decoration.right,
                        child.bottom
                ))

                for (margin in marginList) {
                    addColor(c, margin, backgroundColor)
                }
            }

        }

    }

    private fun addColor(c: Canvas, rect: Rect, color: Int) {

        drawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        drawable?.setBounds(rect.left, rect.top, rect.right, rect.bottom)
        drawable?.draw(c)
    }

    fun getColor(targetColor: String?, context: Context, defaultId: Int): Int {
        targetColor?.let {
            return try {
                Color.parseColor(targetColor)
            } catch (ex: IllegalArgumentException) {
                logD { "Error parsing string color = $targetColor. Throw message = ${ex.message}" }
                ContextCompat.getColor(context, defaultId)
            }
        }
        return ContextCompat.getColor(context, defaultId)
    }
}
