package com.artear.stevedoreviewsexample

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.artear.stevedore.stevedoreitems.presentation.contract.ArtearViewHolder
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearSection
import com.artear.tools.error.NestError


class LoadingViewHolder(itemView: View,
                        private var listener: PagingErrorView.OnReloadClickListener? = null) :
        RecyclerView.ViewHolder(itemView), ArtearViewHolder<LoadingData> {

    private lateinit var progressBar: ProgressBar
    private lateinit var pagingErrorView: PagingErrorView

    override fun bind(model: LoadingData, artearSection: ArtearSection) {
        progressBar = itemView.findViewById<ProgressBar>(R.id.pagingProgressBar)
        pagingErrorView = itemView.findViewById<PagingErrorView>(R.id.pagingErrorView)
        listener?.let { pagingErrorView.setOnReloadClickListener(it) }
    }

    fun setError(error: NestError) {
        pagingErrorView.setErrorType(error.type)
        pagingErrorView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    fun setLoading() {
        pagingErrorView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

}