package com.thiagofr.jsonplaceholder.model

import android.os.Parcel
import android.os.Parcelable

data class CompanyUI(
    val name: String,
    val catchPhrase: String,
    val bs: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(catchPhrase)
        parcel.writeString(bs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyUI> {
        override fun createFromParcel(parcel: Parcel): CompanyUI {
            return CompanyUI(parcel)
        }

        override fun newArray(size: Int): Array<CompanyUI?> {
            return arrayOfNulls(size)
        }
    }
}