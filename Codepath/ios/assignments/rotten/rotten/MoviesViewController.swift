//
//  MoviesViewController.swift
//  rotten
//
//  Created by Praful Mantale on 9/11/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class MoviesViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var tableView: UITableView!
    var url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=zgee42ss9jen5rggamzsaum3"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        
        
        var request = NSURLRequest(URL:NSURL(string: url))
        
        //NSURLConnection.sendAsynchronousRequest(request, queue:NSOperationQueue.mainQueue()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        
        return 100;
    }
    
    func tableView(tableView: UITableView!, cellForRowAtIndexPath indexPath: NSIndexPath!) -> UITableViewCell!{
        
        var cell = tableView.dequeueReusableCellWithIdentifier("MovieCell") as UITableViewCell;
        
//        cell.textLabel!.text = "Hello I am at row: \(indexPath.row), section: \(indexPath.section)"
        
        return cell;
        
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
