package com.artear.cover.coveritem.repository.model.link

import android.os.Parcel
import android.os.Parcelable
import com.artear.cover.coveritem.repository.deserializer.link.LinkDeserializer

import com.google.gson.annotations.JsonAdapter

/**
 * @param url can be empty when is created from article click
 */
@JsonAdapter(LinkDeserializer::class)
data class Link(val url: String, val internal: String) : Parcelable {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(internal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Link> {
        override fun createFromParcel(parcel: Parcel): Link {
            return Link(parcel)
        }

        override fun newArray(size: Int): Array<Link?> {
            return arrayOfNulls(size)
        }
    }
}