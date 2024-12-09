package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import androidx.room.PrimaryKey
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.ID_EVENTO


@Parcelize
class Event(
    var dataEvent: String = "",
    var descricao: String = "",
    var nomePet: String = "",
    var timeMedicine: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = ID_EVENTO,
): Parcelable