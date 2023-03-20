package br.com.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dictionary.databinding.ActivityAddWordBinding

class AddWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}