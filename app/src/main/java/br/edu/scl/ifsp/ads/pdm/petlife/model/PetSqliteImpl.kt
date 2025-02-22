package br.edu.scl.ifsp.ads.pdm.petlife.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.scl.ifsp.ads.pdm.petlife.R
import java.sql.SQLException

class PetSqliteImpl(context: Context) : PetDao {

    companion object {
        private const val PET_DATABASE_FILE = "pet"
        private const val PET_TABLE = "pet"
        private const val NAME_COLUMN = "name"
        private const val DATE_NASC_COLUMN = "date_nasc"
        private const val TYPE_PET_COLUMN = "type_pet"
        private const val SIZE_PET_COLUMN = "size_pet"
        private const val COLOR_PET_COLUMN = "color_pet"

        private const val EVENT_TABLE = "event"
        private const val DATE_EVENT_COLUMN = "event_date"
        private const val DESCRICAO_COLUMN = "descricao_pet"
        private const val MEDICINE_COLUMN = "time_medicine"
        private const val ID_COLUMN = "id_evento"

        private const val CREATE_PET_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $PET_TABLE (" +
                "$NAME_COLUMN TEXT NOT NULL PRIMARY KEY, " +
                "$DATE_NASC_COLUMN TEXT NOT NULL, " +
                "$TYPE_PET_COLUMN TEXT NOT NULL, " +
                "$COLOR_PET_COLUMN TEXT NOT NULL, " +
                "$SIZE_PET_COLUMN TEXT NOT NULL);"

        private const val CREATE_EVENT_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $EVENT_TABLE (" +
                    "$DATE_EVENT_COLUMN TEXT NOT NULL, " +
                    "$DESCRICAO_COLUMN TEXT NOT NULL," +
                    "$NAME_COLUMN TEXT NOT NULL, " +
                    "$MEDICINE_COLUMN TEXT," +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "CONSTRAINT FK_PET_EVENT FOREIGN KEY ($NAME_COLUMN) " +
                    "REFERENCES $PET_TABLE($NAME_COLUMN)" +
                    "ON DELETE CASCADE);" //se um pet for excluido, sera deletado todos os eventos relacionado a ele
    }


    private val petDatabase: SQLiteDatabase = context.openOrCreateDatabase(
        PET_DATABASE_FILE,
        MODE_PRIVATE,
        null
    ).apply {
        execSQL("PRAGMA foreign_keys = ON;")
    }

    init {
        try {
            petDatabase.execSQL(CREATE_PET_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.pet_list), se.toString())
        }
    }

    init {
        try {
            petDatabase.execSQL(CREATE_EVENT_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.event_list), se.toString())
        }
    }

    override fun createPet(pet: Pet) = petDatabase.insert(PET_TABLE, null, petToContentValues(pet))

    override fun retrievePet(nome: String) = cursorToPet(
        petDatabase.rawQuery("SELECT * FROM $PET_TABLE WHERE $NAME_COLUMN = ?", null)
    )


    override fun retrievePets(): MutableList<Pet> {
        val petList = mutableListOf<Pet>()

        val cursor = petDatabase.rawQuery("SELECT * FROM $PET_TABLE", null)
        while (cursor.moveToNext()) {
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


    override fun createEvent(event: Event, nomePet: String): Long {
        val cursor = petDatabase.rawQuery("SELECT MAX(id_evento) FROM event", null)
        var lastId = 0L
        if (cursor.moveToFirst()) {
            lastId = cursor.getLong(0)
        }
        cursor.close()
        event.id = lastId.toInt()+1
        return petDatabase.insert(EVENT_TABLE, null, eventToContentValues(event, nomePet))
    }


    override fun retrieveEvents(nomePet: String): MutableList<Event> {
        val eventList = mutableListOf<Event>()

        val cursor = petDatabase.rawQuery(
            "SELECT * FROM $EVENT_TABLE WHERE $NAME_COLUMN = ?",
            arrayOf(nomePet)
        )
        while (cursor.moveToNext()) {
            eventList.add(cursorToEvent(cursor))
        }
        return eventList
    }

    override fun deleteEvent(id: Int) = petDatabase.delete(
        EVENT_TABLE,
        "$ID_COLUMN = ?",
        arrayOf(id.toString())
    )

    override fun updateEvent(event: Event, nomePet: String) =
        petDatabase.update(
            EVENT_TABLE,
            eventToContentValues(event, nomePet),
            "$ID_COLUMN = ?",
            arrayOf(event.id.toString()))



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

    private fun eventToContentValues(event: Event, nomePet: String) = ContentValues().apply {
        with(event) {
            put(DATE_EVENT_COLUMN, dataEvent)
            put(DESCRICAO_COLUMN, descricao)
            put(NAME_COLUMN, nomePet)
            put(MEDICINE_COLUMN, timeMedicine)
        }
    }

    private fun cursorToEvent(cursor: Cursor) = with(cursor) {
        Event(
            getString(getColumnIndexOrThrow(DATE_EVENT_COLUMN)),
            getString(getColumnIndexOrThrow(DESCRICAO_COLUMN)),
            getString(getColumnIndexOrThrow(NAME_COLUMN)),
            getString(getColumnIndexOrThrow(MEDICINE_COLUMN)),
            getInt(getColumnIndexOrThrow(ID_COLUMN))
        )
    }

}