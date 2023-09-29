package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusResponse(
    var isSuccess: Boolean = true
): Parcelable
