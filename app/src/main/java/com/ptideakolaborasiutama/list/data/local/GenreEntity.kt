package com.ptideakolaborasiutama.list.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GenreEntity(
    val id:Int,
    val name:String
): Parcelable