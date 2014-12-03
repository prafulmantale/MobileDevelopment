//
//  PositionStatusView.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/3/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class PositionStatusView: UIView {
    
    var skip : CGFloat = 1
    var strokeWidth : CGFloat = 3
    var startX : CGFloat = 19
    
    var barHt : CGFloat = 14
    
    
    
    // Only override drawRect: if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func drawRect(rect: CGRect) {
        // Drawing code
        
        
        drawVerticalLine(startX - strokeWidth - 0.5)
        drawIndicators()
        drawVerticalLine(startX - 0.75)
        drawIndicators()
        drawVerticalLine(startX - 0.5)
        
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
        
        var context6 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context6, [213/255,213/255,213/255,1])
        
        CGContextSetLineWidth(context6, CGFloat(strokeWidth))
        
        for i in 1...34{
            CGContextBeginPath(context6)
            CGContextMoveToPoint(context6,  startX, 0)
            CGContextAddLineToPoint(context6, startX, barHt)
            CGContextStrokePath(context6)
            
            startX = startX + strokeWidth + skip
            
        }
        
    }
    
}
