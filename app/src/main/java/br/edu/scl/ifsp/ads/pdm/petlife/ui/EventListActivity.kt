package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.controller.EventoController
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityEventListBinding

import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.PARAMETRO_DADOS
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.ULTIMA_VISITA_VET
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event
import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet

class EventListActivity : AppCompatActivity() {
    private val aelb: ActivityEventListBinding by lazy {
        ActivityEventListBinding.inflate(layoutInflater)
    }

    private var eventNovo: Event = Event(
        dataEvent = "",
        descricao = ""
    )

    private lateinit var eventarl: ActivityResultLauncher<Intent>


    //Controller
    private val mainController: EventoController by lazy {
        EventoController(this)
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

        // Inicializando o intentPet
        //Intent de pet que vira para que consiga pegar o nome do pet
        val intentPet = if (SDK_INT < TIRAMISU) {
            intent.getParcelableExtra<Pet>(PARAMETRO_DADOS)
        } else {
            intent.getParcelableExtra(PARAMETRO_DADOS, Pet::class.java)
        }

        aelb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.event_list)
            setSupportActionBar(it)
        }
        //fillEventList()
        aelb.eventoLv.adapter = eventAdapter

        eventarl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val event = if (SDK_INT < TIRAMISU) {
                        result.data?.getParcelableExtra<Event>(ULTIMA_VISITA_VET)
                    } else {
                        result.data?.getParcelableExtra(ULTIMA_VISITA_VET, Event::class.java)
                    }
                    event?.let { receivedEvent ->
                        // Se o evento não existir, ele é adicionado à lista
                        val position = eventList.indexOfFirst { it.id == receivedEvent.id }
                        if (position == -1) {
                            eventList.add(receivedEvent)
                            intentPet?.let { pet ->
                                mainController.insertEvent(receivedEvent, pet.nome)
                            }
                        }
                        //Se já existir, o pet na lista é atualizado
                        else {
                            eventList[position] = receivedEvent
                            //mainController.modifyPet(receivedEvent)
                        }

                        eventAdapter.notifyDataSetChanged() // avisa a view que teve um novo elemento adicionado a lista
                    }
                }
            }
    }
    //criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_evento, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addEventMi-> {
                Intent("ULTIMA_VISITA_VET").apply {
                    putExtra(ULTIMA_VISITA_VET, eventNovo)
                    eventarl.launch(this)
                }
                true
            }


            else -> {
                false
            }
        }

    }
    private fun fillEventList() {
        for (index in 1..10) {
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