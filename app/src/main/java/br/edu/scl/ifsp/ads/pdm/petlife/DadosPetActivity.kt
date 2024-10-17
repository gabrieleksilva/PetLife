package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.MainActivity.Constantes.PARAMETRO_DADOS
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding

class DadosPetActivity : AppCompatActivity() {
    private val adb: ActivityDadosPetBinding by lazy{
        ActivityDadosPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adb.root)
        setSupportActionBar(adb.toolbarIn.toolbar)

        intent.getParcelableExtra<Pet>(PARAMETRO_DADOS)?.also { parametro ->
            adb.nomeEt.setText(parametro.nome)
            adb.dataEt.setText(parametro.dtNasc)
            adb.corEt.setText(parametro.cor)
        }

        //with(adb) {
            adb.salvarDadosBt.setOnClickListener {

                val dadosPet: Pet = Pet(
                    nome = adb.nomeEt.text.toString(),
                    dtNasc = adb.dataEt.text.toString(),
                    tipo = adb.especieRg.toString(),
                    cor = adb.corEt.text.toString(),
                    porte = adb.porteLl.toString()
                    )

                //criando uma Intent
                Intent().apply {
                        putExtra(PARAMETRO_DADOS, dadosPet)
                    setResult(RESULT_OK, this)
                }
                finish()

            }
        //}
    }
}