import Foundation
import Flutter

class MethodChannelController: FlutterViewController {
    var channel: FlutterMethodChannel?
    var word: Word?
    
    init(engine: FlutterEngine, word: Word){
        super.init(engine: engine, nibName: nil, bundle: nil)
        self.word = word
        
        channel = FlutterMethodChannel(name: "method_channel", binaryMessenger: self.binaryMessenger)
    }
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        channel?.invokeMethod("word_method", arguments: ["word": word?.word, "definition": word?.definition])
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        navigationController?.setNavigationBarHidden(true, animated: true)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(true)
        navigationController?.setNavigationBarHidden(false, animated: true)
    }

}
