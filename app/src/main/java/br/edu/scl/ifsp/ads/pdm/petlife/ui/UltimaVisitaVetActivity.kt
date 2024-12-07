package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.PARAMETRO_VET
import br.edu.scl.ifsp.ads.pdm.petlife.model.UltimaVisitaVet

class UltimaVisitaVetActivity : AppCompatActivity() {
    private val auvet: ActivityUltimaVisitaVetBinding by lazy {
        ActivityUltimaVisitaVetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auvet.root)
        setSupportActionBar(auvet.toolbarIn.toolbar)


        intent.getParcelableExtra<UltimaVisitaVet>(PARAMETRO_VET)?.also { parametro ->
            auvet.dataEt.setText(parametro.dataUltimaVisita)
            auvet.telefoneEt.setText(parametro.telefone)
            auvet.siteEt.setText(parametro.site)

//            when (parametro.tipo) {
//                adb.cachorroRb.text.toString() -> adb.especieRg.check(adb.cachorroRb.id)
//                adb.gatoRb.text.toString() -> adb.especieRg.check(adb.gatoRb.id)
//                else -> ""
//            }
//
//            when (parametro.porte) {
//                adb.pequenoRb.text.toString() -> adb.tamanhoRg.check(adb.pequenoRb.id)
//                adb.medioRb.text.toString() -> adb.tamanhoRg.check(adb.medioRb.id)
//                adb.grandeRb.text.toString() -> adb.tamanhoRg.check(adb.grandeRb.id)
//                else -> ""
//            }
        }

//        intent.getStringExtra(PARAMETRO_VET)?.also { parametro ->
//            auvet.dataEt.setText(parametro)
//        }

        auvet.ultVisitBt.setOnClickListener {
//            Intent().apply {
//                auvet.dataEt.text.toString().let {
//                    putExtra(PARAMETRO_VET, it)
//                }
//                setResult(RESULT_OK, this)
//            }
//            finish()
            val dadosVisitaVet: UltimaVisitaVet = UltimaVisitaVet(
                dataUltimaVisita = auvet.dataEt.text.toString(),
                telefone = auvet.telefoneEt.text.toString(),
                site = auvet.siteEt.text.toString(),
            )

            //criando uma Intent
            Intent().apply {
                putExtra(PARAMETRO_VET, dadosVisitaVet)
                setResult(RESULT_OK, this)
            }
            finish()

        }
    }

}