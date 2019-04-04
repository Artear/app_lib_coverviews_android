package com.artear.cover.articleitem

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.artear.cover.coveritem.presentation.model.ArtearSection
import com.artear.tools.screen.ScreenUtils


class ContentViewHolder(itemView: View, override var listener: ArtearOnClickListener? = null) :
        RecyclerView.ViewHolder(itemView), ArtearViewHolder<ContentData> {

    override fun bind(model: ContentData, blockStyle: BlockStyle, artearSection: ArtearSection) {

        val padding = dpToPx(16)
        val titlePaddingSide = dpToPx(16)
        val titlePaddingTop = dpToPx(20)

        val titleBackgroundColor = Utils.getColor("#FFFFFF", blockStyle.backgroundColor)
        val titleColor = Utils.getColor("#FF000000", blockStyle.titleTextColor)
        val underlineColor = Utils.getColor("#FFA6E7F7", blockStyle.underlineColor)
        val descriptionTextColor = Utils.getColor("#FFFFFF", blockStyle.descriptionTextColor)

        var placeholderResource = R.drawable.placeholder_18_full

        var underlineEnabled = false
        blockStyle.underline?.let {
            underlineEnabled = it
        }

        var descriptionEnabled = false
        blockStyle.descriptionHidden?.let {
            descriptionEnabled = !it
        }

        if (descriptionEnabled && model.description != null && !model.description.isEmpty()) {
            itemView.itemDescription.visibility = VISIBLE
            itemView.itemDescription.setTextColor(descriptionTextColor)

            itemView.itemDescription.setDefaultTextFuture(model.description)
            var descriptionMaxLines = Int.MAX_VALUE
            blockStyle.descriptionNumberLines?.let {
                if (it != 0) {
                    descriptionMaxLines = it
                }
            }
            itemView.itemDescription.maxLines = descriptionMaxLines
        } else {
            itemView.itemDescription.visibility = INVISIBLE
            itemView.itemDescription.text = ""
        }

        itemView.contentTitle.underlineEnabled = underlineEnabled
        itemView.contentTitle.underLineColor = underlineColor

        itemView.itemDescription.underlineEnabled = underlineEnabled
        itemView.itemDescription.underLineColor = underlineColor


        var titleMaxLines = Int.MAX_VALUE
        blockStyle.titleNumberLines?.let {
            if (it != 0) {
                titleMaxLines = it
            }
        }
        itemView.contentTitle.maxLines = titleMaxLines

        itemView.contentTitle.textSize = 20f

        var imageWidth = ScreenUtils.getScreenMinWidth(itemView.context) - (padding * 2)

        if (artearSection.margin != null && artearSection.margin) {
        } else {
            imageWidth = ScreenUtils.getScreenMinWidth(itemView.context)
        }

        var aspectRatio = WIDE_ASPECT_RATIO

        if (blockStyle.weight == 0.5f) {
            itemView.contentTitle.minHeight = dpToPx(72)
            itemView.contentTitle.textSize = 15f
            var itemSpace: Int

            if (artearSection.margin != null && artearSection.margin) {

                placeholderResource = R.drawable.placeholder_18
                itemSpace = dpToPx(24)
            } else {
                placeholderResource = R.drawable.placeholder_19
                itemSpace = dpToPx(8)
                aspectRatio = ImageUtil.WIDER_ASPECT_RATIO
            }
            imageWidth = ScreenUtils.getScreenMinWidth(itemView.context) / 2 - itemSpace
        }

        itemView.apply {
            itemDescription.setPadding(titlePaddingSide, dpToPx(11), titlePaddingSide, titlePaddingTop)
            contentTitle.setPadding(titlePaddingSide, titlePaddingTop, titlePaddingSide, 0)
            contentTitle.setBackgroundColor(titleBackgroundColor)
            itemDescription.setBackgroundColor(titleBackgroundColor)
            contentTitle.setTextColor(titleColor)
            itemDescription.setTextColor(descriptionTextColor)
            contentTitle.setDefaultTextFuture(model.title)
        }

        val imageParams = itemView.contentImage.layoutParams

        imageParams.width = imageWidth
        imageParams.height = (imageWidth * aspectRatio).toInt()

        val imageUrl = ImageUtil.getImageWideUrl(model.imageUrl, imageParams.width, imageParams.height)

        ImageUtil.loadImageIntoView(itemView.contentImage, imageUrl, placeholderResource, null)

        itemView.setOnClickListener {
            listener?.run {
                model.link?.let { link ->
                    onArticleClick(link)
                }
            }
        }

    }

}