package br.edu.scl.ifsp.ads.pdm.petlife.model

interface PetDao {
    fun createPet(pet: Pet): Long //retorna o id da linha.
    fun retrievePet(nome: String): Pet
    fun retrievePets(): MutableList<Pet>
    fun updatePet(pet: Pet): Int
    fun deletePet(nome: String): Int
}