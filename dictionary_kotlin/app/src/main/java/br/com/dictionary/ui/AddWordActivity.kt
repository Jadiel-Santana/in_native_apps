package br.com.dictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dictionary.R
import br.com.dictionary.data.DictionaryRepository
import br.com.dictionary.databinding.ActivityAddWordBinding
import br.com.dictionary.models.Word
import com.google.android.material.snackbar.Snackbar

class AddWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonSave.setOnClickListener {
            addWord()
        }
    }

    private fun addWord() {
        val word = binding.editWord.text.toString()
        val definition = binding.editDefinition.text.toString()

        if (word.isNotEmpty() && definition.isNotEmpty()) {
            DictionaryRepository.add(Word(word, definition))
            finish()
        } else {
            Snackbar.make(binding.root, "Os campos são obrigatórios", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
}