package br.edu.scl.ifsp.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.petlife.databinding.ActivityUltimaVisitaPetShopBinding

class UltimaVisitaPetShopActivity : AppCompatActivity() {
    private val ups: ActivityUltimaVisitaPetShopBinding by lazy{
        ActivityUltimaVisitaPetShopBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ups.root)
    }
}