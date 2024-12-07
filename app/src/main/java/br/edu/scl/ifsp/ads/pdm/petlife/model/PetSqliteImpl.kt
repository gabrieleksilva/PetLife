package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
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
                "$SIZE_PET_COLUMN TEXT NOT NULL, " +
                "$COLOR_PET_COLUMN TEXT NOT NULL );"
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
    private fun petToContentValues(pet: Pet) = ContentValues().apply {
        with(pet) {
            put(NAME_COLUMN, nome)
            put(DATE_NASC_COLUMN, dtNasc)
            put(TYPE_PET_COLUMN, tipo)
            put(SIZE_PET_COLUMN, porte)
            put(COLOR_PET_COLUMN, cor)
        }
    }


}