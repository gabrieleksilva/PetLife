package br.edu.scl.ifsp.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding

class DadosPetActivity : AppCompatActivity() {
    private val adb: ActivityDadosPetBinding by lazy{
        ActivityDadosPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adb.root)
    }
}