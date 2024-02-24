package com.thiagofr.jsonplaceholder.model

import android.os.Parcel
import android.os.Parcelable

data class AlbumUI(
    val id: Long,
    val userId: Long,
    val title: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString().orEmpty()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(userId)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumUI> {
        override fun createFromParcel(parcel: Parcel): AlbumUI {
            return AlbumUI(parcel)
        }

        override fun newArray(size: Int): Array<AlbumUI?> {
            return arrayOfNulls(size)
        }
    }

}
