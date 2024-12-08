package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.TileEventBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event

class EventAdapter(context: Context,
                   private val eventList: MutableList<Event>):
    ArrayAdapter<Event>(context, R.layout.tile_event, eventList) {
    private data class EventTileHolder(
        val dataTv: TextView,
        val descTv: TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        lateinit var teb: TileEventBinding

        // pegar o evento que vai ser usado para preencher a celula
        val event = eventList[position]

        //verificando se a celula ja foi inflada ou nao
        var eventTile = convertView

        if (eventTile == null) {
            //Se nao foi, infla uma nova celula
            teb = TileEventBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            eventTile = teb.root

            // Criar um holder para a nova celula
            val newEventTileHolder = EventTileHolder(
                teb.dataTv,
                teb.descricaoTv
            )

            eventTile.tag = newEventTileHolder
        }
        return eventTile
    }
}