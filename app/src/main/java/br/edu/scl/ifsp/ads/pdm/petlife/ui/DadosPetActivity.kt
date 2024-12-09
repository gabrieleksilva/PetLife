package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R

import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant
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

        // se for falso ou vazio ele vai para o modo visualização e nao modo edicao
        val viewMode = intent.getBooleanExtra(Constant.VIEW_MODE, false)

        val receivedPet = intent.getParcelableExtra<Pet>(PARAMETRO_DADOS)

        receivedPet?.let { pet ->
            with(adb) {
                // Preenchendo os campos com os dados recebidos
                //modo edicao
                nomeEt.setText(pet.nome)
                dataEt.setText(pet.dtNasc)
                corEt.setText(pet.cor)

                nomeEt.isFocusable = !viewMode



                when (pet.tipo) {
                    cachorroRb.text.toString() -> {
                        especieRg.check(cachorroRb.id)
                        tipoAnimal = cachorroRb.text.toString()
                    }

                    gatoRb.text.toString() -> {
                        especieRg.check(gatoRb.id)
                        tipoAnimal = gatoRb.text.toString()
                    }
                }
                when (pet.porte) {
                    pequenoRb.text.toString() -> {
                        tamanhoRg.check(pequenoRb.id)
                        porteAnimal = pequenoRb.text.toString()
                    }

                    medioRb.text.toString() -> {
                        tamanhoRg.check(medioRb.id)
                        porteAnimal = medioRb.text.toString()
                    }

                    grandeRb.text.toString() -> {
                        tamanhoRg.check(grandeRb.id)
                        porteAnimal = grandeRb.text.toString()
                    }
                }
            }
        }


        adb.especieRg.setOnCheckedChangeListener { _, checkedId ->
            tipoAnimal = when (checkedId) {
                R.id.cachorroRb -> adb.cachorroRb.text.toString()
                R.id.gatoRb -> adb.gatoRb.text.toString()
                else -> ""
            }
        }
        adb.tamanhoRg.setOnCheckedChangeListener { _, checkedId ->
            porteAnimal = when (checkedId) {
                R.id.pequenoRb -> adb.pequenoRb.text.toString()
                R.id.medioRb -> adb.medioRb.text.toString()
                R.id.grandeRb -> adb.grandeRb.text.toString()
                else -> ""
            }
        }

        adb.salvarDadosBt.setOnClickListener {
            val pet = Pet(
                adb.nomeEt.text.toString(),
                adb.dataEt.text.toString(),
                tipoAnimal,
                adb.corEt.text.toString(),
                porteAnimal
            )
            Intent().apply {
                putExtra(PARAMETRO_DADOS, pet)
                setResult(RESULT_OK, this)
                finish()
            }
        }
    }
}