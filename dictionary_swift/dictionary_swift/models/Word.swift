import Foundation

class Word: NSObject {
    let word: String
    let definition: String
    
    init(word: String, definition: String) {
        self.word = word
        self.definition = definition
    }
}
