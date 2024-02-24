package com.thiagofr.jsonplaceholder.model

import android.os.Parcel
import android.os.Parcelable

data class UserUI(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressUI?,
    val phone: String,
    val website: String,
    val company: CompanyUI?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readParcelable(AddressUI::class.java.classLoader),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readParcelable(CompanyUI::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeParcelable(address, flags)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeParcelable(company, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserUI> {
        override fun createFromParcel(parcel: Parcel): UserUI {
            return UserUI(parcel)
        }

        override fun newArray(size: Int): Array<UserUI?> {
            return arrayOfNulls(size)
        }
    }
}

