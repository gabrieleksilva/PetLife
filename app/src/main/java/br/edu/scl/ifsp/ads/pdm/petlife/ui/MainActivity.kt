package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.PARAMETRO_DADOS
import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private lateinit var dadosarl: ActivityResultLauncher<Intent>
    private var petNovo: Pet = Pet(
        nome = "",
        dtNasc = "",
        tipo = "",
        cor = "",
        porte = ""
    )


    // Data source
    private val petList: MutableList<Pet> = mutableListOf()

    //Adapter
    private val petAdapter: PetAdapter by lazy {
        PetAdapter(this, petList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        amb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.pet_list)
            setSupportActionBar(it)
        }
        fillPetList()
        amb.petsLv.adapter = petAdapter

        dadosarl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {

                    result.data?.getParcelableExtra<Pet>(PARAMETRO_DADOS)?.let { pet ->
                        //preencheCamposMain(pet)
                        preencheCamposDadosPet(pet)
                    }
                }
            }

        registerForContextMenu(amb.petsLv) // para chamar o menu flutuante
    }

    //criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) = menuInflater.inflate(R.menu.context_menu_main, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewMi -> {
                Intent("DADOS_PET").apply {
                    putExtra(PARAMETRO_DADOS, petNovo)
                    dadosarl.launch(this)
                }
                true
            }


            else -> {
                false
            }
        }

    }


    private fun preencheCamposDadosPet(pet: Pet) {
        petNovo.tipo = pet.tipo
        petNovo.nome = pet.nome
        petNovo.porte = pet.porte
        petNovo.dtNasc = pet.dtNasc
        petNovo.cor = pet.cor
    }



    private fun fillPetList() {
        for (index in 1..20) {
            //populando a lista com valores estaticos
            petList.add(
                Pet(
                    "Nome $index",
                    "Data Nasc $index",
                    "Tipo $index",
                    "Cord $index",
                    "Porte $index"
                )
            )
        }
    }

}