package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event(
    var dataEvent: String = "",
    var descricao: String = "",
): Parcelable