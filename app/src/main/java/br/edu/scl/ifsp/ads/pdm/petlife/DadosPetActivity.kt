package br.edu.scl.ifsp.ads.pdm.petlife

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityDadosPetBinding
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityMainBinding

class DadosPetActivity : AppCompatActivity() {
    private val adb: ActivityDadosPetBinding by lazy{
        ActivityDadosPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(adb.root)
    }
}