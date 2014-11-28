//
//  Welcome2ViewController.swift
//  GooglePlay
//
//  Created by Praful Mantale on 11/27/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class Welcome2ViewController: UIViewController {

    @IBAction func prevButton(sender: AnyObject) {
        
        dismissViewControllerAnimated(true, completion:nil)
        
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
