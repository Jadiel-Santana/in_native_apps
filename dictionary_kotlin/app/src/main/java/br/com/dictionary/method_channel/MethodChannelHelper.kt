package br.com.dictionary.method_channel

import br.com.dictionary.models.Word
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class MethodChannelHelper(private val binaryMessenger: BinaryMessenger) {

    private val channel = MethodChannel(binaryMessenger, Constants.Flutter.METHOD_CHANNEL)

    fun setWord(word: Word) {
        channel.invokeMethod(
            Constants.Flutter.SET_WORD_METHOD,
            mapOf("word" to word.word, "definition" to word.definition)
        )
    }
}