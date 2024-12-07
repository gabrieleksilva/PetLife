package br.edu.scl.ifsp.ads.pdm.petlife.model;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pet(
        var nome: String,
        var dtNasc: String,
        var tipo: String,
        var cor: String,
        var porte: String
): Parcelable
