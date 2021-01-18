package com.yulindawuland_18102252.praktikum11.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.security.Timestamp

}

@Parcelize
data class Quote(
    var id: String? = null,
    var title: String? = null,
    var description: String? = null,
    var category: String? = null,
    var date: Timestamp? = null
) : Parcelable