//
//  ViewController.swift
//  ymlistview
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class ViewController: UIViewController , UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var tableData = [String]()
    
    
//    @IBOutlet weak var rateCcyView: UIView!
//    @IBOutlet weak var rateView: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
//        rateCcyView.layer.cornerRadius = 5;
//        rateCcyView.clipsToBounds = true
//        
//        rateView.layer.cornerRadius = 5;
//        rateView.clipsToBounds = true;
        
        tableView.delegate = self;
        tableView.dataSource = self;
        
        // Register the UITableViewCell class with the tableView
        
        
//        self.tableView?.registerClass(UITableViewCell.self, forCellReuseIdentifier: "custcell")
        
        // Setup table data
//        for index in 0...100 {
//            self.tableData.append("Item \(index)")
//        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("custcell") as CustCellView
        
        
        
        return cell
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10;
    }
    
    


}

