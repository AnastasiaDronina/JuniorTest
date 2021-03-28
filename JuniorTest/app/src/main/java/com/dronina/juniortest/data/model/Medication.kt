package com.dronina.juniortest.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medication(
    val id: Int = 0,
    val title: String = "",
    val icon: String = "",
    val isReadyForKids: Boolean = false
) : Parcelable