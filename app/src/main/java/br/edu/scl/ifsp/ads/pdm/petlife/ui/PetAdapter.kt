package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet

class PetAdapter (context: Context,
                  private val petList: MutableList<Pet>):
    ArrayAdapter<Pet>(context, R.layout.tile_pet, petList) {
    private data class PetTileHolder(
        val nameTv: TextView,
        val typeTv: TextView
    )
}