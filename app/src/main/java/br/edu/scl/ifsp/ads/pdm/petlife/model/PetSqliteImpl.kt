package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.scl.ifsp.ads.pdm.petlife.R
import java.sql.SQLException

class PetSqliteImpl(context: Context) :PetDao {

    companion object {
        private const val PET_DATABASE_FILE = "pet"
        private const val PET_TABLE = "pet"
        private const val NAME_COLUMN = "name"
        private const val DATE_NASC_COLUMN = "date_nasc"
        private const val TYPE_PET_COLUMN = "type_pet"
        private const val SIZE_PET_COLUMN = "size_pet"
        private const val COLOR_PET_COLUMN = "color_pet"


        private const val CREATE_PET_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $PET_TABLE (" +
                "$NAME_COLUMN TEXT NOT NULL PRIMARY KEY, " +
                "$DATE_NASC_COLUMN TEXT NOT NULL, " +
                "$TYPE_PET_COLUMN TEXT NOT NULL, " +
                "$COLOR_PET_COLUMN TEXT NOT NULL, " +
                "$SIZE_PET_COLUMN TEXT NOT NULL);"
    }

    private val petDatabase: SQLiteDatabase = context.openOrCreateDatabase(
        PET_DATABASE_FILE,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            petDatabase.execSQL(CREATE_PET_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.toString())
        }
    }

    override fun createPet(pet: Pet) =
        petDatabase.insert(PET_TABLE, null, petToContentValues(pet))

    override fun retrievePet(nome: String) = cursorToPet(petDatabase.
    rawQuery("SELECT * FROM $PET_TABLE WHERE $NAME_COLUMN = ?", null))


    override fun retrievePets(): MutableList<Pet> {
        val petList = mutableListOf<Pet>()

        val cursor = petDatabase.rawQuery("SELECT * FROM $PET_TABLE", null)
        while (cursor.moveToNext()){
            petList.add(cursorToPet(cursor))
        }
        return petList
    }

    override fun updatePet(pet: Pet) = petDatabase.update(
        PET_TABLE,
        petToContentValues(pet),
        "$NAME_COLUMN = ?",
        arrayOf(pet.nome)
    )

    override fun deletePet(nome: String) = petDatabase.delete(
        PET_TABLE,
        "$NAME_COLUMN = ?",
        arrayOf(nome))

    private fun petToContentValues(pet: Pet) = ContentValues().apply {
        with(pet) {
            put(NAME_COLUMN, nome)
            put(DATE_NASC_COLUMN, dtNasc)
            put(TYPE_PET_COLUMN, tipo)
            put(COLOR_PET_COLUMN, cor)
            put(SIZE_PET_COLUMN, porte)
        }
    }
    private fun cursorToPet(cursor: Cursor) = with(cursor) {
        Pet(
            getString(getColumnIndexOrThrow(NAME_COLUMN)),
            getString(getColumnIndexOrThrow(DATE_NASC_COLUMN)),
            getString(getColumnIndexOrThrow(TYPE_PET_COLUMN)),
            getString(getColumnIndexOrThrow(COLOR_PET_COLUMN)),
            getString(getColumnIndexOrThrow(SIZE_PET_COLUMN))
        )
    }


}