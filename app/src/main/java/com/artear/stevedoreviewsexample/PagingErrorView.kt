package com.artear.stevedoreviewsexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.artear.tools.error.NestErrorType
import com.artear.ui.extensions.setTextAppearanceSafe
import com.artear.ui.model.ErrorButton
import com.artear.ui.model.ErrorImage
import com.artear.ui.model.ErrorMessage
import com.artear.ui.views.error.ErrorCustomizer
import kotlinx.android.synthetic.main.paging_error_view.view.*

class PagingErrorView : ConstraintLayout, View.OnClickListener {

    private var onReloadClickListener: OnReloadClickListener? = null
    private var errorCustomizer: ErrorCustomizer? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.paging_error_view, this, true)
        errorActionButton.setOnClickListener(this)
    }

    fun setErrorCustomizer(customizer: ErrorCustomizer) {
        errorCustomizer = customizer
    }

    fun setErrorType(errorType: NestErrorType) {
        errorCustomizer?.customizeError(errorType) { image, message, button ->
            updateViews(image, message, button)
        }
    }

    @SuppressLint("ResourceType")
    private fun updateViews(image: ErrorImage, message: ErrorMessage,
                            button: ErrorButton?) {
        errorImage.setImageDrawable(getDrawable(errorImage.context, image.resId))
        errorMessage.setText(message.title)
        errorMessage.setTextAppearanceSafe(message.styleId)
        errorActionButton.visibility = if (button != null) View.VISIBLE else View.GONE
        button?.let {
            //Ensure that colorBackground is a @ColorRes int
            it.colorBackground?.let { color ->
                errorActionButton.setBackgroundColor(ContextCompat.getColor(context, color))
            }
        }
    }

    private fun getDrawable(context: Context, drawableResId: Int): Drawable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getDrawable(drawableResId)
        } else {
            try {
                VectorDrawableCompat.create(context.resources, drawableResId, null)
            } catch (e: Resources.NotFoundException) {
                ContextCompat.getDrawable(context, drawableResId)
            }
        }
    }

    override fun onClick(v: View) {
        onReloadClickListener?.onReload()
    }

    fun setOnReloadClickListener(onReloadClickListener: OnReloadClickListener) {
        this.onReloadClickListener = onReloadClickListener
    }

    fun clearOnReloadClickListener() {
        this.onReloadClickListener = null
    }

    interface OnReloadClickListener {
        fun onReload()
    }

}
