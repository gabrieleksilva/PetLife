package br.edu.scl.ifsp.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVacinaBinding

class UltimaVacinaActivity : AppCompatActivity() {
    private val auv: ActivityUltimaVacinaBinding by lazy{
        ActivityUltimaVacinaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(auv.root)
    }
}