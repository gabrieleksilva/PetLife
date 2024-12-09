package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.ULTIMA_VISITA_VET
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event

class UltimaVisitaVetActivity : AppCompatActivity() {
    private val auvet: ActivityUltimaVisitaVetBinding by lazy {
        ActivityUltimaVisitaVetBinding.inflate(layoutInflater)
    }
    private var eventoPet: String = " "
    private var nomePet: String = " "
    private var idPet: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auvet.root)

        // se for falso ou vazio ele vai para o modo visualização e nao modo edicao
        val viewMode = intent.getBooleanExtra(Constant.VIEW_MODE, false)
        val receivedEvent = intent.getParcelableExtra<Event>(ULTIMA_VISITA_VET)

        receivedEvent?.let { ultimaVisitaVet ->
            idPet = receivedEvent.id
            nomePet = receivedEvent.nomePet
            with(auvet) {
                dataEt.setText(ultimaVisitaVet.dataEvent)

                when (ultimaVisitaVet.descricao) {
                    vetRb.text.toString() -> {
                        eventoRg.check(vetRb.id)
                        eventoPet = vetRb.text.toString()
                    }

                    vacinaRb.text.toString() -> {
                        eventoRg.check(vacinaRb.id)
                        eventoPet = vacinaRb.text.toString()
                    }

                    petshopRb.text.toString() -> {
                        eventoRg.check(petshopRb.id)
                        eventoPet = petshopRb.text.toString()
                    }
                }
            }
        }

        auvet.eventoRg.setOnCheckedChangeListener { _, checkedId ->
            eventoPet = when (checkedId) {
                R.id.vetRb -> auvet.vetRb.text.toString()
                R.id.vacinaRb -> auvet.vacinaRb.text.toString()
                R.id.petshopRb -> auvet.petshopRb.text.toString()
                else -> ""
            }
        }

        auvet.ultVisitBt.setOnClickListener {
            val event = Event(
                auvet.dataEt.text.toString(),
                eventoPet,
                nomePet,
                idPet

            )
            Intent().apply {
                putExtra(ULTIMA_VISITA_VET, event)
                setResult(RESULT_OK, this)
                finish()
            }
        }

        //modo visualização
        auvet.run {
            dataEt.isEnabled = !viewMode
            vetRb.isEnabled = !viewMode
            vacinaRb.isEnabled = !viewMode
            petshopRb.isEnabled = !viewMode


            ultVisitBt.visibility = if (viewMode) GONE else View.VISIBLE

        } }
}