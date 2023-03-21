package br.com.dictionary.models

data class Word(val word: String, val definition: String) {

    override fun toString(): String {
        return "Word(word='$word', definition='$definition')"
    }
}