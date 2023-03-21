package br.com.dictionary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import br.com.dictionary.CustomAdapter
import br.com.dictionary.R
import br.com.dictionary.data.DictionaryRepository
import br.com.dictionary.databinding.ActivityMainBinding
import br.com.dictionary.models.Word

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        createList()

        binding.fabAddWord.setOnClickListener {
            goToAddWordPage()
        }
    }

    private fun createList() {
        adapter = CustomAdapter(this)

        binding.dictionaryList.adapter = adapter

        binding.dictionaryList.emptyView = findViewById(R.id.empty_text);

        binding.dictionaryList.setOnItemClickListener { _, _, position, _ ->
            goToWordDetail(position)
        };
    }

    private fun goToAddWordPage() {
        val intent = Intent(this, AddWordActivity::class.java)
        startActivity(intent)
    }

    private fun goToWordDetail(position: Int) {
        val word = DictionaryRepository.items[position]
        Log.i("TAG", word.toString())
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}