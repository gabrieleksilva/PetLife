package br.edu.scl.ifsp.ads.pdm.petlife.controller

import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet
import br.edu.scl.ifsp.ads.pdm.petlife.model.PetDao
import br.edu.scl.ifsp.ads.pdm.petlife.model.PetSqliteImpl
import br.edu.scl.ifsp.ads.pdm.petlife.ui.MainActivity

class MainController(mainActivity: MainActivity) {
    private val petDao: PetDao = PetSqliteImpl(mainActivity)

    fun insertPet(pet: Pet) = petDao.createPet(pet)
    fun getPet(isbn: String) = petDao.retrievePet(isbn)
    fun getPets() = petDao.retrievePets()
    fun modifyPet(pet: Pet) = petDao.updatePet(pet)
    fun removePet(isbn: String) = petDao.deletePet(isbn)
}