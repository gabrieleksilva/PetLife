package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes { // classe interna na main
        const val PARAMETRO_VACINA = "PARAMETRO_VACINA"
        const val PARAMETRO_VET = "PARAMETRO_VET"
    }
    private lateinit var parl: ActivityResultLauncher<Intent>
    private lateinit var varl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.toolbarIn.toolbar)
        parl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                result.data?.getStringExtra(PARAMETRO_VACINA)?.let {
                    amb.dataVacinaTv.text = it
                }
            }
        }

        varl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                result.data?.getStringExtra(PARAMETRO_VET)?.let {
                    amb.dataVetTv.text = it
                }
            }
        }
    }
    //criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
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
                    amb.dataVetTv.text.toString().let {
                        putExtra(PARAMETRO_VET, it)
                    }
                    varl.launch(this)
                }
                true
            }
            else -> { false }
        }
    }
}