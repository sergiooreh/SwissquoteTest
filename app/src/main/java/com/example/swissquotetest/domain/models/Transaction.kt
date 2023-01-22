package com.example.swissquotetest.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.example.swissquotetest.domain.models.enums.Category
import com.example.swissquotetest.domain.models.enums.Status


data class Transaction(
    val id: Int,
    val amount: String,
    val category: Category,
    val currency: String,
    val merchand: String,
    val date: String,
    val status: Status
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        Category.valueOf(parcel.readString() ?: ""),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        Status.valueOf(parcel.readString() ?: "")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(amount)
        parcel.writeString(category.name)
        parcel.writeString(currency)
        parcel.writeString(merchand)
        parcel.writeString(date)
        parcel.writeString(status.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transaction> {
        override fun createFromParcel(parcel: Parcel): Transaction {
            return Transaction(parcel)
        }

        override fun newArray(size: Int): Array<Transaction?> {
            return arrayOfNulls(size)
        }
    }
}