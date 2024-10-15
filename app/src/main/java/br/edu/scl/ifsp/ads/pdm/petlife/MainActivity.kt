package br.edu.scl.ifsp.ads.pdm.petlife

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import android.content.Intent.ACTION_PICK
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_INTENT
import android.content.Intent.EXTRA_TITLE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ToolbarBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes { // classe interna na main
        const val PARAMETRO_EXTRA = "PARAMETRO_EXTRA"
    }
    private lateinit var parl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.toolbarIn.toolbar)
        parl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                result.data?.getStringExtra(PARAMETRO_EXTRA)?.let {
                    amb.dataVacinaEt.text = it
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
                    amb.dataVacinaEt.text.toString().let {
                        putExtra(PARAMETRO_EXTRA, it)
                    }
                    parl.launch(this)
                }
                true
            }
            else -> { false }
        }
    }
}