package br.edu.scl.ifsp.ads.pdm.petlife;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UltimaVisitaVet(
        var dataUltimaVisita: String,
        var telefone: String,
        var site: String,
): Parcelable
