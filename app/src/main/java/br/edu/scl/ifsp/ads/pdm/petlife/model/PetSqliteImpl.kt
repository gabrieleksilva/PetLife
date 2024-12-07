package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.content.Context

class PetSqliteImpl(context: Context) :PetDao {
    override fun createPet(pet: Pet): Long {
        TODO("Not yet implemented")
    }

    override fun retrievePet(nome: String): Pet {
        TODO("Not yet implemented")
    }

    override fun retrievePets(): MutableList<Pet> {
        TODO("Not yet implemented")
    }

    override fun updatePet(pet: Pet): Int {
        TODO("Not yet implemented")
    }

    override fun deletePet(nome: String): Int {
        TODO("Not yet implemented")
    }
}