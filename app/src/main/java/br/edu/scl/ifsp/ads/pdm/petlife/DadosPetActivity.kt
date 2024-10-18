package br.edu.scl.ifsp.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.MainActivity.Constantes.PARAMETRO_DADOS
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding

class DadosPetActivity : AppCompatActivity() {
    private val adb: ActivityDadosPetBinding by lazy{
        ActivityDadosPetBinding.inflate(layoutInflater)
    }
    private var tipoAnimal: String = " "
    private var porteAnimal: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adb.root)
        setSupportActionBar(adb.toolbarIn.toolbar)

        intent.getParcelableExtra<Pet>(PARAMETRO_DADOS)?.also { parametro ->
            adb.nomeEt.setText(parametro.nome)
            adb.dataEt.setText(parametro.dtNasc)
            adb.corEt.setText(parametro.cor)
        }

        adb.especieRg.setOnCheckedChangeListener { group, checkedId ->
            tipoAnimal = when (checkedId) {
                R.id.cachorroRb -> adb.cachorroRb.text.toString()
                R.id.gatoRb -> adb.gatoRb.text.toString()
                else -> ""  // Nenhum botão selecionado
            }
        }
        adb.porteSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                porteAnimal= (view as TextView).text.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }

        //with(adb) {
            adb.salvarDadosBt.setOnClickListener {

                val dadosPet: Pet = Pet(
                    nome = adb.nomeEt.text.toString(),
                    dtNasc = adb.dataEt.text.toString(),
                    tipo = tipoAnimal,
                    cor = adb.corEt.text.toString(),
                    porte = porteAnimal
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