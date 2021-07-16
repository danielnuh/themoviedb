package com.ptideakolaborasiutama.list.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String,
    val rate:Float
):Parcelable