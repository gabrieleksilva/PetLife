package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityEventListBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event

class EventListActivity : AppCompatActivity() {
    private val aelb: ActivityEventListBinding by lazy {
        ActivityEventListBinding.inflate(layoutInflater)
    }
    // Data source
    private val eventList: MutableList<Event> = mutableListOf()
    //Adapter
    private val eventAdapter: EventAdapter by lazy {
        EventAdapter(this, eventList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aelb.root)

        aelb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.event_list)
            setSupportActionBar(it)
        }
        fillEventList()
        aelb.eventoLv.adapter = eventAdapter


    }
    private fun fillEventList(){
        for (index in 1..10){
            //populando a lista com valores estaticos
            eventList.add(
                Event(
                    "Data evento $index",
                    "descricao $index"
                )
            )
        }
    }
}