//
//  PositionStatusView.swift
//  rotten
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class PositionStatusView: UIView {
    
    var skip : CGFloat = 1
    var strokeWidth : CGFloat = 3
    var startX : CGFloat = 19
    
    var barHt : CGFloat = 14
    var shortInds : PositionIndicators = PositionIndicators()
    var longInds : PositionIndicators = PositionIndicators()
    
    
    var contextsInitialized : Bool = false

    

    // Only override drawRect: if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func drawRect(rect: CGRect) {
        
        if(contextsInitialized == false){
            shortInds.update(34, currentPos: 0, maxPos: 100)
            longInds.update(34, currentPos: 0, maxPos: 100)
            contextsInitialized = true
        }
        // Drawing code
        var dotterStart : CGFloat = 19 - strokeWidth
        
        var fvl : CGFloat = startX - strokeWidth - 0.5
        drawVerticalLine(fvl)
        //drawIndicators()
        //drawVerticalLine(startX - 0.75)
        drawIndicators()
        drawVerticalLine(startX - 0.5)
        drawVerticalLine((fvl + startX - 0.5)/2 )
        drawVerticalLine((fvl + startX - 0.5)/4 )
        drawVerticalLine((fvl + startX - 0.5) * 3/4 )
        
        drawDottedLines()
        
    }
    
    func drawDottedLines(){
        var context9 = UIGraphicsGetCurrentContext()
        
        CGContextSetStrokeColor(context9, [0,0,0,1])
        CGContextSetLineDash(context9, 0.0, [4,2], 2);
        CGContextSetLineWidth(context9, CGFloat(0.5))
        CGContextMoveToPoint(context9,  19 - strokeWidth, 20)
        CGContextSetLineCap(context9, kCGLineCapRound)
        CGContextAddLineToPoint(context9, startX, 20)
        
        CGContextStrokePath(context9)
    }
    
    func drawVerticalLine(var startX : CGFloat){
        var centerHt : CGFloat = 27
        var context9 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context9, [0,0,0,1])
        
        CGContextSetLineWidth(context9, CGFloat(0.5))
        CGContextMoveToPoint(context9,  startX, 0)
        CGContextAddLineToPoint(context9, startX, centerHt)
        CGContextStrokePath(context9)
    }

    func drawIndicators(){
        
        
        var contextNormal = UIGraphicsGetCurrentContext()
        
      
        var contextBlank = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(contextBlank, [213/255,213/255,213/255,1])
        CGContextSetLineWidth(contextBlank, CGFloat(strokeWidth))
        
        if(shortInds.blankCount > 0){
        for i in 1...shortInds.blankCount{
            CGContextBeginPath(contextBlank)
            CGContextMoveToPoint(contextBlank,  startX, 0)
            CGContextAddLineToPoint(contextBlank, startX, barHt)
            CGContextStrokePath(contextBlank)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
         var contextDanger = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(contextDanger, [193/255,30/255,0,1])
        CGContextSetLineWidth(contextDanger, CGFloat(strokeWidth))
        if(shortInds.dangerCount > 0) {
        for i in 1...shortInds.dangerCount{
            CGContextBeginPath(contextDanger)
            CGContextMoveToPoint(contextDanger,  startX, 0)
            CGContextAddLineToPoint(contextDanger, startX, barHt)
            CGContextStrokePath(contextDanger)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
        var contextWarning = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(contextWarning, [255/255,135/255,0,1])
        CGContextSetLineWidth(contextWarning, CGFloat(strokeWidth))
        
        if(shortInds.warningCount > 0 ){
        for i in 1...shortInds.warningCount{
            CGContextBeginPath(contextWarning)
            CGContextMoveToPoint(contextWarning,  startX, 0)
            CGContextAddLineToPoint(contextWarning, startX, barHt)
            CGContextStrokePath(contextWarning)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
        CGContextSetStrokeColor(contextNormal, [37/255,75/255,196/255,1])
        CGContextSetLineWidth(contextNormal, CGFloat(strokeWidth))
        
        if(shortInds.normalCount + longInds.normalCount > 0 ){
        
        for i in 1...shortInds.normalCount + longInds.normalCount{
            CGContextBeginPath(contextNormal)
            CGContextMoveToPoint(contextNormal,  startX, 0)
            CGContextAddLineToPoint(contextNormal, startX, barHt)
            CGContextStrokePath(contextNormal)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
        CGContextSetStrokeColor(contextWarning, [255/255,135/255,0,1])
        CGContextSetLineWidth(contextWarning, CGFloat(strokeWidth))
        
        if(longInds.warningCount > 0 ){
        
        for i in 1...longInds.warningCount{
            CGContextBeginPath(contextWarning)
            CGContextMoveToPoint(contextWarning,  startX, 0)
            CGContextAddLineToPoint(contextWarning, startX, barHt)
            CGContextStrokePath(contextWarning)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
        CGContextSetStrokeColor(contextDanger, [193/255,30/255,0,1])
        CGContextSetLineWidth(contextDanger, CGFloat(strokeWidth))
        if(longInds.dangerCount > 0 ){

        
        for i in 1...longInds.dangerCount{
            CGContextBeginPath(contextDanger)
            CGContextMoveToPoint(contextDanger,  startX, 0)
            CGContextAddLineToPoint(contextDanger, startX, barHt)
            CGContextStrokePath(contextDanger)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
        
        CGContextSetStrokeColor(contextBlank, [213/255,213/255,213/255,1])
        CGContextSetLineWidth(contextBlank, CGFloat(strokeWidth))

        if(longInds.blankCount > 0){

        
        for i in 1...longInds.blankCount{
            CGContextBeginPath(contextBlank)
            CGContextMoveToPoint(contextBlank,  startX, 0)
            CGContextAddLineToPoint(contextBlank, startX, barHt)
            CGContextStrokePath(contextBlank)
            
            startX = startX + strokeWidth + skip
            
        }
        }
        
    }
    
    
    func setCurrentPosition(currentPosition : Double){
        
        if(currentPosition < 0){
            shortInds.update(34, currentPos: currentPosition, maxPos: 100)
            longInds.update(34, currentPos: 0, maxPos: 100)
        }
        else{
            longInds.update(34, currentPos: currentPosition, maxPos: 100)
            shortInds.update(34, currentPos: 0, maxPos: 100)
        }
        println("")
        println("\(shortInds.blankCount)  \(shortInds.normalCount) \(shortInds.warningCount) \(shortInds.dangerCount)")
        println("\(longInds.blankCount)  \(longInds.normalCount) \(longInds.warningCount) \(longInds.dangerCount)")
        
        startX = 19
        setNeedsDisplay()
    }

}

class PositionIndicators {
    var blankCount : Int = 0
    var normalCount : Int = 0
    var warningCount : Int = 0
    var dangerCount : Int = 0
    
    func update(maxCount : Int, currentPos: Double, maxPos : Double){
        
        var percentage : Double = Double( maxCount) * currentPos/maxPos
        
        percentage = abs(percentage)
        
        var filledCount : Int = Int(percentage)
        
        println("Update : \(maxCount) \(currentPos) \(maxPos) \(percentage) \(filledCount)")
        
        if(filledCount < 0){
            filledCount = 0
        }
        
        if(filledCount > maxCount){
            filledCount = Int(maxCount)
        }
        
        blankCount = maxCount - filledCount;
        
        if(filledCount == 0){
            
            normalCount = 0;
            warningCount = 0;
            dangerCount = 0;
        }
        else if(filledCount <= 17){
            
            normalCount = filledCount;
            warningCount = 0;
            dangerCount = 0;
        }
        else if(filledCount < 33){
            
            normalCount = 17;
            warningCount = 34 - blankCount - normalCount;
            dangerCount = 0;
        }
        else{
            
            normalCount = 17;
            warningCount = 15;
            dangerCount = 34 - blankCount - normalCount - warningCount;
        }
    }
}
