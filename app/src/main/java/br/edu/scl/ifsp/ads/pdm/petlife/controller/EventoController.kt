package br.edu.scl.ifsp.ads.pdm.petlife.controller

import br.edu.scl.ifsp.ads.pdm.petlife.model.Event
import br.edu.scl.ifsp.ads.pdm.petlife.model.PetDao
import br.edu.scl.ifsp.ads.pdm.petlife.model.PetSqliteImpl
import br.edu.scl.ifsp.ads.pdm.petlife.ui.EventListActivity

class EventoController(eventListActivity: EventListActivity) {
    private val petDao: PetDao = PetSqliteImpl(eventListActivity)

    fun insertEvent(event: Event, nome: String) = petDao.createEvent(event, nome)
    fun getEvents(nomePet: String) = petDao.retrieveEvents(nomePet)
}