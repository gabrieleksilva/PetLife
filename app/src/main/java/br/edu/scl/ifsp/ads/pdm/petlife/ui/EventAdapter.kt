package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event

class EventAdapter(context: Context,
                   private val eventList: MutableList<Event>):
    ArrayAdapter<Event>(context, R.layout.tile_event, eventList) {
    private data class PetTileHolder(
        val dataTv: TextView,
        val descTv: TextView
    )
}