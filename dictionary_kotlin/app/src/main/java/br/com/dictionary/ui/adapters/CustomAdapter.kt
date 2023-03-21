package br.com.dictionary.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.com.dictionary.R
import br.com.dictionary.data.DictionaryRepository
import br.com.dictionary.models.Word


class CustomAdapter(context: Context) : ArrayAdapter<Word>(context, 0, DictionaryRepository.items as List<Word>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentItemView = convertView

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        }

        val currentNumberPosition: Word? = getItem(position)

        val wordText = currentItemView?.findViewById<TextView>(R.id.word_text)
        wordText?.text = currentNumberPosition?.word

        val definitionText = currentItemView?.findViewById<TextView>(R.id.definition_text)
        definitionText?.text = currentNumberPosition?.definition

        return currentItemView!!
    }
}