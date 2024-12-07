package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R

import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.PARAMETRO_DADOS
import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet

class DadosPetActivity : AppCompatActivity() {
    private val adb: ActivityDadosPetBinding by lazy {
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
            tipoAnimal = parametro.tipo
            porteAnimal = parametro.porte
            when (parametro.tipo) {
                adb.cachorroRb.text.toString() -> adb.especieRg.check(adb.cachorroRb.id)
                adb.gatoRb.text.toString() -> adb.especieRg.check(adb.gatoRb.id)
                else -> ""
            }

            when (parametro.porte) {
                adb.pequenoRb.text.toString() -> adb.tamanhoRg.check(adb.pequenoRb.id)
                adb.medioRb.text.toString() -> adb.tamanhoRg.check(adb.medioRb.id)
                adb.grandeRb.text.toString() -> adb.tamanhoRg.check(adb.grandeRb.id)
                else -> ""
            }
        }

        adb.especieRg.setOnCheckedChangeListener { group, checkedId ->
            tipoAnimal = when (checkedId) {
                R.id.cachorroRb -> adb.cachorroRb.text.toString()
                R.id.gatoRb -> adb.gatoRb.text.toString()
                else -> ""  // Nenhum botão selecionado
            }
        }
        adb.tamanhoRg.setOnCheckedChangeListener { group, checkedId ->
            porteAnimal = when (checkedId) {
                R.id.pequenoRb -> adb.pequenoRb.text.toString()
                R.id.medioRb -> adb.medioRb.text.toString()
                R.id.grandeRb -> adb.grandeRb.text.toString()
                else -> ""  // Nenhum botão selecionado
            }
        }


        with(adb) {
            salvarDadosBt.setOnClickListener {

                val dadosPet: Pet = Pet(
                    nome = nomeEt.text.toString(),
                    dtNasc = dataEt.text.toString(),
                    tipo = tipoAnimal,
                    cor = corEt.text.toString(),
                    porte = porteAnimal
                )

                //criando uma Intent
                Intent().apply {
                    putExtra(PARAMETRO_DADOS, dadosPet)
                    setResult(RESULT_OK, this)
                }
                finish()

            }
        }
    }
}