package br.com.dictionary.data

import br.com.dictionary.models.Word

class DictionaryRepository private constructor() {
    companion object {
        val items = mutableListOf<Word>()

        fun add(word: Word) {
            items.add(word)
        }
    }
}