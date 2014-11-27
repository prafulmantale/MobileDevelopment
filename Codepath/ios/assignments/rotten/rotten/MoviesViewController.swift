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
    
    
    
    var movies: [NSDictionary] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        
//        var url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=zgee42ss9jen5rggamzsaum3&limit=20&country=us"
        
        var url = "https://api.instagram.com/v1/media/search?lat=48.858844&lng=2.294351&access_token=1494410638.1fb234f.fa70833370b9413a855979b011090de8"
        
        var request = NSURLRequest(URL: NSURL(string: url)!)
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue.mainQueue()){(response : NSURLResponse!, data:  NSData!, error : NSError!) -> Void in
            
            println("data: \(data)")
            println("error: \(error)")
            var object = NSJSONSerialization.JSONObjectWithData(data, options: nil, error: nil) as NSDictionary
        
            println("object \(object)")
            self.movies = object["movies"] as [NSDictionary]
            
            self.tableView.reloadData()
            
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int{
        
        return movies.count;
    }
    
    func tableView(tableView: UITableView!, cellForRowAtIndexPath indexPath: NSIndexPath!) -> UITableViewCell!{
        
        var cell = tableView.dequeueReusableCellWithIdentifier("MovieCell") as MovieViewCell;
        
//        cell.textLabel!.text = "Hello I am at row: \(indexPath.row), section: \(indexPath.section)"
        
        var movie = movies[indexPath.row]
        
        cell.movieTitleLabel.text = movie["title"] as? String
        
        cell.synopsisLabel.text = movie["synopsis"] as? String
        
        var posters = movie["posters"] as NSDictionary
        
        var posterUrl = posters["thumbnail"] as String
        
        cell.posterView.setImageWithURL(NSURL(string: posterUrl))
        
        
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
