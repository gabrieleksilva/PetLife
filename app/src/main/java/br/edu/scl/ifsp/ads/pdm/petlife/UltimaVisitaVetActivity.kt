package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.MainActivity.Constantes.PARAMETRO_VET
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding

class UltimaVisitaVetActivity : AppCompatActivity() {
    private val auvet: ActivityUltimaVisitaVetBinding by lazy{
        ActivityUltimaVisitaVetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auvet.root)
        setSupportActionBar(auvet.toolbarIn.toolbar)

        intent.getStringExtra(PARAMETRO_VET)?.also { parametro ->
            auvet.dataEt.setText(parametro)
        }

        auvet.ultVisitBt.setOnClickListener{
            Intent().apply {
                auvet.dataEt.text.toString().let {
                    putExtra(PARAMETRO_VET, it)
                }
                setResult(RESULT_OK,this)
            }
            finish()
        }
    }

}