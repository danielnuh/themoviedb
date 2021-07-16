package com.ptideakolaborasiutama.list.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieDetailEntity(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val stars: Float,
    val genres: List<GenreEntity>
): Parcelable