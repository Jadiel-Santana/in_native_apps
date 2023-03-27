import UIKit
import Flutter

class ViewController: UIViewController, UITableViewDataSource {
    private let table: UITableView = {
        let table = UITableView()
        table.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        return table
    }()
    
    var dictionary = [Word] ()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = "Dictionary Swift"
        view.addSubview(table)
        table.dataSource = self
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(didTapAdd))
    }
    
    @objc private func didTapAdd() {
        let alert = UIAlertController(title: "Adicionar palavra", message: "Insira uma nova palavra", preferredStyle: .alert)
        
        alert.addTextField{
            field in field.placeholder = "Digite uma Palavra"
        }
        alert.addTextField{
            field in field.placeholder = "Digite uma Definição"
        }
        alert.addAction(UIAlertAction(title: "Cancelar", style: .cancel, handler: nil))
        alert.addAction(UIAlertAction(title: "Salvar", style: .default, handler: { (_) in
            guard let textFields = alert.textFields else { return }
                                               
            if let word = textFields[0].text, !word.isEmpty {
                if let definition = textFields[1].text, !definition.isEmpty {
                    let item = Word(word: word, definition: definition)
                        
                    self.dictionary.append(item)
                    self.table.reloadData()
                }
            }
        }))
        
        present(alert, animated: true)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        table.frame = view.bounds
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dictionary.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        
        cell.textLabel?.text = "\(dictionary[indexPath.row].word) - \(dictionary[indexPath.row].definition)"
        
        let press = UITapGestureRecognizer(target: self, action: #selector(openFlutter(_ :)))
        cell.addGestureRecognizer(press)
        
        return cell
    }
    
    @objc func openFlutter(_ gesture: UITapGestureRecognizer){
        guard let cell = gesture.view as? UITableViewCell else {return}
        guard let index = self.table.indexPath(for: cell)?.row else {return}


        guard let flutterEngine = (UIApplication.shared.delegate as? AppDelegate)?.flutterEngine else {return}
        let flutterViewController = MethodChannelController(engine: flutterEngine, word: dictionary[index])

        navigationController?.pushViewController(flutterViewController, animated: true)
    }
}

