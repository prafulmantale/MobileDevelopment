//
//  ViewController.swift
//  FirstSwiftProgram
//
//  Created by Praful Mantale on 7/31/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    let lanugageName : String = "AnyLanguage";
    var version : Double = 1.0;
    var feelingGood: Bool = true;
    
    let interprete = "Language"; //Interprested as String
    var checkVersion = true; //Interpreted as Boolean
    var age = 10; //Interpreted as int
    
    let components = "~/Documents/Swift".pathComponents; //Array of "~" "Documents" "Swift"
    var names : [String] = ["praful", "Reyansh", "Ritu"];//Array typed to String
    var names2 = ["Praful",43, true];//Array of anything that can be converted to string
    var ages = ["Praful" : 34, "Ritu" : 35, "Reyansh": 1]; //Dictionary
    
    
    //Supports unicodes like pie, images etc
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        
        for character in "Praful" {
            println(character);
        }
        
        let a = 3;
        let b = 5;
        let result = "\(a) times \(b) is \( a * b)";
        println("result:" + result);
        
        for comp in components {
            println(comp);
        }
        
       
        
        for var d = 0; d < 15; ++d {
            println(d);
        }
        
        for number in 1...5 {
            println(number);
        }
        
        for u in ["praful", "reyansh", "ritu"]{
            println("Hello \(u)");
        }
        
        for (n , a) in ages {
            println("Age of \(n) is \(a)");
        }
        
        println(names[1]);
        
        names += ["Kumar", "Mantale"];
        println(names[3]);
        
        names[2...4]=["what", "is"]
        for name in names {
            println(name);
        }
        
        var age : Int? = ages["tinku"];
        
        if(age == nil){
            println("No age for tinku");
        }
        age = ages["Praful"];
        
        if(age != nil){
            println(" age for Praful is \(age)");
        }
        
        let count  = 4;
        
        switch count {
            case 1...5:
                println("case1");
            
            default:
                println("default case");
        }
        
        sayHello();
        sayHello(name: "Praful");
        let greeting = greetHello(name: "Tinku");
        println(greeting);
        
        let (status : String, retCode : Int) = statue();
        println("status is \(status) and code is \(retCode)");
        
        let ret = statue();
        println("Values \(ret.code) and \(ret.status)" );
        
        //Closures
        
        let hello = {
            println("Closure hello");
        }
        
        let hello2 : () -> () = {
            println("Hello 2");
        }
        
        hello2();
        
        
        func repeat(count : Int , task : () -> ()){
            
            for i in 0...count {
                task()
            }
        }
        
        repeat(5, {println("task");});
        
        let someVehicle = Vehicle();
        let otherVehicle : Vehicle = Vehicle();
        otherVehicle.numberOfWheels = 4;
        println(someVehicle.description);
        println(otherVehicle.description);
        
        let car : Car = Car();
        println("Car: \(car.description)");
        
        var pt = Point(x:10, y:11);
        var sz = Size(l: 2, w: 3);
        
        var rct = Rect(origin: pt, size: sz);
        rct.moveToRight();
        
        println(Planet.Earth.toRaw());
        
        var stack = Stack<Int>(elements:[Int]())
        stack.push(50);
        println(stack.pop());
        
    }
    
//    func sayHello() {
//        println("Hello");
//    }
    
    func sayHello(name : String = "World"){
        println("Hello \(name)");
    }
    
    func greetHello(name : String = "World") -> String{
        return "Helloji \(name)";
    }
    
    func statue() -> (status: String,code : Int){
        return ("OK", 200);
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

