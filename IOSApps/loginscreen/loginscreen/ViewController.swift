//
//  ViewController.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var loginButton: UIButton!
    
    @IBOutlet var loginContainer: UIView!
    
    @IBOutlet weak var loginPanel: UIView!
    
    
    @IBAction func doLogin(sender: AnyObject) {
        
        let loginRequest = LoginRequest(orgName: "YMSBADemo", userName: "RWPraful", password: "Test123")
        
        println("login request is : \(loginRequest.getJsonString())")
        
        
        var request = NSMutableURLRequest(URL: NSURL(string: "https://demo.ym.integral.net/fxi/admin/auth/login")!, cachePolicy: NSURLRequestCachePolicy.ReloadIgnoringLocalCacheData, timeoutInterval: 5)
        
        request.HTTPMethod = "POST"
        let postString = loginRequest.getJsonString()
        
        request.HTTPBody = postString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: true)
        
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.HTTPShouldHandleCookies = true
        
        var response: NSURLResponse?
        var error: NSError?
        
        NSURLConnection.sendSynchronousRequest(request, returningResponse: &response, error: &error)
        
        // look at the response
        if let httpResponse = response as? NSHTTPURLResponse {
            println("HTTP response: \(httpResponse.statusCode) response:\(httpResponse)")
            getRWSnapShot()
        } else {
            println("No HTTP response")
        }
        
    }
    
    func getRWSnapShot(){
        var request = NSMutableURLRequest(URL: NSURL(string: "https://demo.ym.integral.net/fxi/fxiapi/riskwarehouse/snapshot?org=YMSBADemo")!, cachePolicy: NSURLRequestCachePolicy.ReloadIgnoringLocalCacheData, timeoutInterval: 5)
        
        request.HTTPMethod = "GET"
        
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.HTTPShouldHandleCookies = true
        
        var response: NSURLResponse?
        var error: NSError?
        
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue.mainQueue()){ (response : NSURLResponse!, data : NSData!, error: NSError!) in
            
                var obj = NSJSONSerialization.JSONObjectWithData(data, options: nil, error: nil) as NSDictionary
            
                println("\(obj)")
            
            }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        loginButton.layer.cornerRadius = 5
        loginButton.clipsToBounds = true
        
        loginPanel.layer.masksToBounds = false;
        loginPanel.layer.cornerRadius = 8; // if you like rounded corners
        loginPanel.layer.shadowOffset = CGSizeMake(-15, 20);
        loginPanel.layer.shadowRadius = 5;
        loginPanel.layer.shadowOpacity = 0.3;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

