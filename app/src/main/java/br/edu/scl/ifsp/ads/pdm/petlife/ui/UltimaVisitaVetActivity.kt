package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.EVENT_LIST
import br.edu.scl.ifsp.ads.pdm.petlife.model.Event

class UltimaVisitaVetActivity : AppCompatActivity() {
    private val auvet: ActivityUltimaVisitaVetBinding by lazy {
        ActivityUltimaVisitaVetBinding.inflate(layoutInflater)
    }
    private var eventoPet: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auvet.root)


        val receivedEvent = intent.getParcelableExtra<Event>(EVENT_LIST)

        receivedEvent?.let { ultimaVisitaVet ->
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

        auvet.eventoRg.setOnCheckedChangeListener{ _, checkedId ->
            eventoPet = when (checkedId) {
                R.id.vetRb -> auvet.vetRb.text.toString()
                R.id.vacinaRb -> auvet.vacinaRb.text.toString()
                R.id.petshopRb -> auvet.petshopRb.text.toString()
                else -> ""
            }
        }

    }

}