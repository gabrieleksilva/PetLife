package br.edu.scl.ifsp.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding

class UltimaVisitaVetActivity : AppCompatActivity() {
    private val uva: ActivityUltimaVisitaVetBinding by lazy{
        ActivityUltimaVisitaVetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(uva.root)
    }
}