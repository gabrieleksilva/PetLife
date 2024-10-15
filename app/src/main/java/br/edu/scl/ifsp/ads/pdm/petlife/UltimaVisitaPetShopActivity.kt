package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.MainActivity.Constantes.PARAMETRO_PETSHOP
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaPetShopBinding

class UltimaVisitaPetShopActivity : AppCompatActivity() {
    private val aups: ActivityUltimaVisitaPetShopBinding by lazy{
        ActivityUltimaVisitaPetShopBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aups.root)
        setSupportActionBar(aups.toolbarIn.toolbar)

        intent.getStringExtra(PARAMETRO_PETSHOP)?.also { parametro ->
            aups.dataEt.setText(parametro)
        }
        aups.ultVisitPetBt.setOnClickListener{
            Intent().apply {
                aups.dataEt.text.toString().let {
                    putExtra(PARAMETRO_PETSHOP, it)
                }
                setResult(RESULT_OK, this)//para devolver o valor para a main que sera inserido no campo texto da tela de parametro
            }
            finish()
        }
    }
}