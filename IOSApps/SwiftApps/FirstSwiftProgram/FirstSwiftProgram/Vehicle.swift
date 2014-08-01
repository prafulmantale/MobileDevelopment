//
//  Vehicle.swift
//  FirstSwiftProgram
//
//  Created by Praful Mantale on 7/31/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

class Vehicle{
    
    init(){
        numberOfWheels = 6;
    }
    //Property
    var numberOfWheels = 0;
    
    //Computed property
    var description : String {
        get{
            return "Number of wheels \(numberOfWheels)";
        }
    }
    
}

class Car : Vehicle{
    
    init() {
        super.init();
        numberOfWheels = 4;
        printSomething();
    }
    
//    override var description : String {
//        return " Vehicle is car and wheels are \(numberOfWheels)";
//    }
    
    override var numberOfWheels : Int{
    
    willSet{
        //New value
        if(newValue > 0){
            println("Setting new Value \(newValue)");
        }
    }
    didSet{
        //old value
        
        println("The old Value \(oldValue)");
    }
    }
    
    func printSomething(){
        self.numberOfWheels = 1;
    }
    
}


struct Point{
    var x, y :Double;
}

struct Size{
    var l,w:Double;
}

struct Rect{
    var origin : Point;
    var size : Size;
    
    var area : Double{
        return size.l * size.w;
    }
    
    mutating func moveToRight(){
        origin.x += 20;
    }
}

enum Planet : Int{
    case M = 1, Earth, Venus;
}

struct Stack<T>{
    var elements = [T]();
    
    mutating func push(elelment : T){
        elements.append(elelment);
        
    }
    
    mutating func pop() -> T{
        return elements.removeLast();
    }
}