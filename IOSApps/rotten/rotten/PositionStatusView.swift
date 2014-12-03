//
//  PositionStatusView.swift
//  rotten
//
//  Created by Praful Mantale on 12/2/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class PositionStatusView: UIView {


    // Only override drawRect: if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func drawRect(rect: CGRect) {
        // Drawing code
        
        let center = CGPointMake(self.frame.size.width/2, self.frame.height - 10)
        var skip : CGFloat = 1
        var strokeWidth : CGFloat = 3
        var startX : CGFloat = skip + strokeWidth + 15
        var centerHt : CGFloat = 27
        var barHt : CGFloat = 14
        
        var context7 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context7, [0,0,0,1])
        
        CGContextSetLineWidth(context7, CGFloat(0.5))
        
        CGContextBeginPath(context7)
        CGContextMoveToPoint(context7,  startX - strokeWidth, 0)
        CGContextAddLineToPoint(context7, startX - strokeWidth, centerHt)
        CGContextStrokePath(context7)
        
        
        
        var context5 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context5, [1,0,0,1])
        
        CGContextSetLineWidth(context5, CGFloat(strokeWidth))
        
        for i in 1...34{
        CGContextBeginPath(context5)
        CGContextMoveToPoint(context5,  startX, 0)
        CGContextAddLineToPoint(context5, startX, barHt)
        CGContextStrokePath(context5)
        
            startX = startX + strokeWidth + skip
            
        }
        
        var context8 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context8, [0,0,0,1])
        
        CGContextSetLineWidth(context8, CGFloat(0.5))
        CGContextBeginPath(context8)
            CGContextMoveToPoint(context8,  startX - 0.75, 0)
            CGContextAddLineToPoint(context8, startX - 0.75, centerHt)
            CGContextStrokePath(context8)
            
        
            
        
        var context6 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context6, [0,0,1,1])
        
        CGContextSetLineWidth(context6, CGFloat(strokeWidth))
        
        for i in 1...34{
            CGContextBeginPath(context6)
            CGContextMoveToPoint(context6,  startX, 0)
            CGContextAddLineToPoint(context6, startX, barHt)
            CGContextStrokePath(context6)
            
            startX = startX + strokeWidth + skip
            
        }
        
        var context9 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context9, [0,0,0,1])
        
        CGContextSetLineWidth(context9, CGFloat(0.5))
        CGContextMoveToPoint(context9,  startX - 0.5, 0)
        CGContextAddLineToPoint(context9, startX - 0.5, centerHt)
        CGContextStrokePath(context9)
        
    }


}
