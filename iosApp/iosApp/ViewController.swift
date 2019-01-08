import UIKit
import app

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var items: [Language] = []

    lazy var tableView: UITableView = {
        let tableView = UITableView(frame: UIScreen.main.bounds)

        return tableView
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(tableView)
        tableView.dataSource = self
        tableView.delegate = self
        getLanguages()
    }

    func getLanguages() {
        URLSession.shared.dataTask(with: URL(string: "https://vpered.su/v1/groups")!) { (data, response, _) in
            let dictionary = try? JSONSerialization.jsonObject(with: data ?? Data(), options: []) as? [[String: Any]]

            self.items = dictionary??.reduce(into: [Language](), { (res, groupDict) in
                let langs = groupDict["languages"] as! [[String: Any]]
                let langNames = langs.map({ (langDict: [String: Any]) -> Language in
                    return Language(fullName: langDict["fullName"] as! String, code: langDict["code"] as! String)
                })
                res += langNames
            }) ?? []
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }

        }.resume()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell") ?? UITableViewCell()
        DispatchQueue.main.async {
            cell.textLabel?.text = self.items[indexPath.row].fullName
        }
        return cell
    }
}
