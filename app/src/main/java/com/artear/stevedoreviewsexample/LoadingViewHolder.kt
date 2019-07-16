package com.artear.stevedoreviewsexample

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.artear.stevedore.stevedoreitems.presentation.contract.ArtearViewHolder
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItemDecoration
import com.artear.tools.error.NestError
import com.artear.tools.error.NestErrorType
import com.artear.ui.model.ErrorButton
import com.artear.ui.model.ErrorImage
import com.artear.ui.model.ErrorMessage
import com.artear.ui.views.error.ErrorCustomizer


class LoadingViewHolder(itemView: View,
                        private var listener: PagingErrorView.OnReloadClickListener? = null) :
        RecyclerView.ViewHolder(itemView), ArtearViewHolder<LoadingData> {

    private lateinit var progressBar: ProgressBar
    private lateinit var pagingErrorView: PagingErrorView

    override fun bind(model: LoadingData, artearItemDecoration: ArtearItemDecoration) {
        progressBar = itemView.findViewById(R.id.pagingProgressBar)
        pagingErrorView = itemView.findViewById(R.id.pagingErrorView)
        pagingErrorView.setErrorCustomizer(getErrorCustomizer())
        listener?.let { pagingErrorView.setOnReloadClickListener(it) }
    }

    private fun getErrorCustomizer(): ErrorCustomizer {
        val errorCustomizer = ErrorCustomizer()
        val networkingItem = errorCustomizer
                .newItemBuilder(NestErrorType.CONNECTION)
                .addMessage(ErrorMessage(R.string.error_connection_description))
                .addImage(ErrorImage(R.drawable.ic_error_black_24dp))
                .addButton(ErrorButton(R.string.error_button_text))
                .build()
        val defaultError = errorCustomizer
                .newItemBuilder(NestErrorType.SERVER)
                .setByDefault()
                .addMessage(ErrorMessage(R.string.error_server_description))
                .addImage(ErrorImage(R.drawable.ic_error_black_24dp))
                .addButton(ErrorButton(R.string.error_button_text))
                .build()
        errorCustomizer.addItem(networkingItem)
        errorCustomizer.addItem(defaultError)
        return errorCustomizer
    }

    fun setError(error: NestError) {
        pagingErrorView.setErrorType(error.type)
        pagingErrorView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    fun setLoading() {
        pagingErrorView.visibility = GONE
        progressBar.visibility = VISIBLE
    }

}