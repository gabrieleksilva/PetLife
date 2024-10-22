package br.edu.scl.ifsp.ads.pdm.petlife

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.toolbarIn.toolbar)

        piarl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == RESULT_OK) {
                resultado.data?.data?.let {
                    startActivity(Intent(ACTION_VIEW, it))
                }
            }
        }
        pcarl = registerForActivityResult(ActivityResultContracts.RequestPermission())
        { permissaoConcedida ->
            if (permissaoConcedida) {
                chamarOuDiscar(true)
            }
            else {
                Toast.makeText(this, "Permissão necessária!", Toast.LENGTH_SHORT).show()
            }
        }
        parl =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.getStringExtra(PARAMETRO_VACINA)?.let {
                        amb.dataVacinaTv.text = it
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
                        amb.dataPetShopTv.text = it
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
        amb.ligacaoBt.setOnClickListener{
            if (checkSelfPermission(CALL_PHONE) == PERMISSION_GRANTED) {
                chamarOuDiscar(false)
            }
            else {
                pcarl.launch(CALL_PHONE)
            }
            true
        }
        amb.siteBt.setOnClickListener{
            val url: Uri = Uri.parse(amb.siteTv.text.toString())
            val navegadorIntent = Intent(ACTION_VIEW, url)
            startActivity(navegadorIntent)
        }

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
                Intent("ULTIMA_VACINA").apply {
                    amb.dataVacinaTv.text.toString().let {
                        putExtra(PARAMETRO_VACINA, it)
                    }
                    parl.launch(this)
                }
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
                Intent("ULTIMA_VISITA_PETSHOP").apply {
                    amb.dataPetShopTv.text.toString().let {
                        putExtra(PARAMETRO_PETSHOP, it)
                    }
                    petarl.launch(this)
                }
                true
            }

            else -> {
                false
            }
        }

    }

    private fun preencheCamposMain(pet: Pet) {
        amb.nomeTv.text = pet.nome
        amb.dataTv.text = pet.dtNasc
        amb.corTv.text = pet.cor
        amb.especieTv.text = pet.tipo
        amb.porteTv.text = pet.porte
    }

    private fun preencheCamposDadosPet(pet: Pet) {
        petNovo.tipo = pet.tipo
        petNovo.nome = pet.nome
        petNovo.porte = pet.porte
        petNovo.dtNasc = pet.dtNasc
        petNovo.cor = pet.cor
    }

    private fun preencherDadosVetMain(dados: UltimaVisitaVet) {
        amb.dataVetTv.text = dados.dataUltimaVisita
        amb.telefoneTv.text = dados.telefone
        amb.siteTv.text = dados.site
    }

    private fun preencheCamposDadosVeterinario(dados: UltimaVisitaVet) {
        dadosVisita.dataUltimaVisita = dados.dataUltimaVisita
        dadosVisita.telefone = dados.telefone
        dadosVisita.site = dados.site
    }

    private fun chamarOuDiscar(chamar: Boolean){
        Uri.parse("tel: ${amb.telefoneTv.text.toString()}").let{
            val discarIntent = Intent(if(chamar) ACTION_CALL else ACTION_CALL).apply {
                data = it
                startActivity(this)
            }
        }
    }
}