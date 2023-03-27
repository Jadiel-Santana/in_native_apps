package br.com.dictionary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import br.com.dictionary.ui.adapters.CustomAdapter
import br.com.dictionary.R
import br.com.dictionary.data.DictionaryRepository
import br.com.dictionary.databinding.ActivityMainBinding
import br.com.dictionary.method_channel.Constants
import br.com.dictionary.method_channel.MethodChannelHelper
import br.com.dictionary.models.Word

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Word>
    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createFlutterEngine()

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

    private fun createFlutterEngine() {
        flutterEngine = FlutterEngine(this)

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache
            .getInstance()
            .put(Constants.Flutter.FLUTTER_ENGINE_ID, flutterEngine)
    }

    private fun goToWordDetail(position: Int) {
        val word = DictionaryRepository.items[position]
        MethodChannelHelper(flutterEngine.dartExecutor.binaryMessenger).setWord(word)

        startActivity(
            FlutterActivity
                .withCachedEngine(Constants.Flutter.FLUTTER_ENGINE_ID)
                .build(this)
        )
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}