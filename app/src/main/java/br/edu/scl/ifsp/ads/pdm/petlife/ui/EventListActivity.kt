package br.edu.scl.ifsp.ads.pdm.petlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.R
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityEventListBinding
import br.edu.scl.ifsp.ads.pdm.petlife.model.Constant.EVENT_LIST
import br.edu.scl.ifsp.ads.pdm.petlife.model.Pet

class EventListActivity : AppCompatActivity() {
    private val aelb: ActivityEventListBinding by lazy {
        ActivityEventListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aelb.root)

        aelb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.event_list)
            setSupportActionBar(it)
        }
    }
}