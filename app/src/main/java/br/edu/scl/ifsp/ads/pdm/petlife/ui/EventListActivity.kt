package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityEventListBinding
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaVetBinding

class EventListActivity : AppCompatActivity() {
    private val aelb: ActivityEventListBinding by lazy {
        ActivityEventListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aelb.root)
    }
}