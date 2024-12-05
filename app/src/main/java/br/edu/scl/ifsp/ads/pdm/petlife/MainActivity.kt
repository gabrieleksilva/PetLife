package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes { // classe interna na main
        const val PARAMETRO_VACINA = "PARAMETRO_VACINA"
        const val PARAMETRO_VET = "PARAMETRO_VET"
        const val PARAMETRO_PETSHOP = "PARAMETRO_PETSHOP"
        const val PARAMETRO_DADOS = "PARAMETRO_DADOS"
    }

    private lateinit var parl: ActivityResultLauncher<Intent>
    private lateinit var pcarl: ActivityResultLauncher<String>
    private lateinit var piarl: ActivityResultLauncher<Intent>
    private lateinit var varl: ActivityResultLauncher<Intent>
    private lateinit var petarl: ActivityResultLauncher<Intent>
    private lateinit var dadosarl: ActivityResultLauncher<Intent>
    private var petNovo: Pet = Pet(
        nome = "",
        dtNasc = "",
        tipo = "",
        cor = "",
        porte = ""
    )
    private var dadosVisita: UltimaVisitaVet = UltimaVisitaVet(
        dataUltimaVisita = "",
        telefone = "",
        site = ""
    )

    // Data source
    private val petList: MutableList<Pet> = mutableListOf()

    //Adapter
    private val petAdapter: ArrayAdapter<String> by lazy {


        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            petList.run{
                val petTitleList: MutableList<String> = mutableListOf()
                this.forEach{ petTitleList.add(it.nome)}
                petTitleList
            }
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        amb.toolbarIn.toolbar.let{
            it.subtitle = getString(R.string.pet_list)
            setSupportActionBar(it)
        }
        fillPetList()
        amb.petsLv.adapter =  petAdapter
/*
        piarl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == RESULT_OK) {
                resultado.data?.data?.let {
                    startActivity(Intent(ACTION_VIEW, it))
                }
            }
        }

        parl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.getStringExtra(PARAMETRO_VACINA)?.let {
//                        amb.dataVacinaTv.text = it
                    }
                }
            }

        varl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.getParcelableExtra<UltimaVisitaVet>(PARAMETRO_VET)?.let { dados ->
                        preencherDadosVetMain(dados)
                        preencheCamposDadosVeterinario(dados)
                    }
                }
            }

        petarl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.getStringExtra(PARAMETRO_PETSHOP)?.let {
//                        amb.dataPetShopTv.text = it
                    }
                }
            }

        dadosarl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {

                    result.data?.getParcelableExtra<Pet>(PARAMETRO_DADOS)?.let { pet ->
                        preencheCamposMain(pet)
                        preencheCamposDadosPet(pet)
                    }
                }
            }
*/

    }

    //criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dadosMi -> {
                Intent("DADOS_PET").apply {
                    putExtra(PARAMETRO_DADOS, petNovo)
                    dadosarl.launch(this)
                }
                true
            }

            R.id.vacinacaoMi -> {
                /*Intent("ULTIMA_VACINA").apply {
                    amb.dataVacinaTv.text.toString().let {
                        putExtra(PARAMETRO_VACINA, it)
                    }
                    parl.launch(this)
                }*/
                true
            }

            R.id.idaAoVetMi -> {


                Intent("ULTIMA_VISITA_VET").apply {
                    putExtra(PARAMETRO_VET, dadosVisita)
                    varl.launch(this)
                }
                true
            }

            R.id.petshopMi -> {
                /*Intent("ULTIMA_VISITA_PETSHOP").apply {
                    amb.dataPetShopTv.text.toString().let {
                        putExtra(PARAMETRO_PETSHOP, it)
                    }
                    petarl.launch(this)
                }*/
                true
            }

            else -> {
                false
            }
        }

    }

    private fun preencheCamposMain(pet: Pet) {
        /*amb.nomeTv.text = pet.nome
        amb.dataTv.text = pet.dtNasc
        amb.corTv.text = pet.cor
        amb.especieTv.text = pet.tipo
        amb.porteTv.text = pet.porte*/
    }

    private fun preencheCamposDadosPet(pet: Pet) {
        petNovo.tipo = pet.tipo
        petNovo.nome = pet.nome
        petNovo.porte = pet.porte
        petNovo.dtNasc = pet.dtNasc
        petNovo.cor = pet.cor
    }

    private fun preencherDadosVetMain(dados: UltimaVisitaVet) {
        /*amb.dataVetTv.text = dados.dataUltimaVisita
        amb.telefoneTv.text = dados.telefone
        amb.siteTv.text = dados.site*/
    }

    private fun preencheCamposDadosVeterinario(dados: UltimaVisitaVet) {
        dadosVisita.dataUltimaVisita = dados.dataUltimaVisita
        dadosVisita.telefone = dados.telefone
        dadosVisita.site = dados.site
    }
    private fun fillPetList(){
        for (index in 1..20){
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