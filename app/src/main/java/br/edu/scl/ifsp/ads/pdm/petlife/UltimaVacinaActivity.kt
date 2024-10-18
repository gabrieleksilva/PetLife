package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.MainActivity.Constantes.PARAMETRO_VACINA
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVacinaBinding

class UltimaVacinaActivity : AppCompatActivity() {
    private val auv: ActivityUltimaVacinaBinding by lazy {
        ActivityUltimaVacinaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auv.root)
        //adicionando a toobar para aparecer a logo da Petlife na parte superior da tela
        setSupportActionBar(auv.toolbarIn.toolbar)

        intent.getStringExtra(PARAMETRO_VACINA)?.also { parametro ->
            auv.dataVacinaTv.setText(parametro)
        }
        auv.ultVacinaBt.setOnClickListener {
            //criando uma Intent
            Intent().apply {
                auv.dataVacinaTv.text.toString().let {
                    putExtra(PARAMETRO_VACINA, it)
                }
                setResult(
                    RESULT_OK,
                    this
                )//para devolver o valor para a main que sera inserido no campo texto da tela de parametro
            }
            finish()
        }

    }
}