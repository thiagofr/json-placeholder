package com.thiagofr.jsonplaceholder.model

import android.os.Parcel
import android.os.Parcelable

data class AddressUI(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(city)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressUI> {
        override fun createFromParcel(parcel: Parcel): AddressUI {
            return AddressUI(parcel)
        }

        override fun newArray(size: Int): Array<AddressUI?> {
            return arrayOfNulls(size)
        }
    }
}