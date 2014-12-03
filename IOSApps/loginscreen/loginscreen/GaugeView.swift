//
//  GaugeView.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/3/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import UIKit

class GaugeView: UIView {

    override func drawRect(rect: CGRect) {
        // Drawing code
        
        var context = UIGraphicsGetCurrentContext()
        var context2 = UIGraphicsGetCurrentContext()
        var context3 = UIGraphicsGetCurrentContext()
        
        //CGContextSetStrokeColor(context, [1,0,0,1])
        //CGContextBeginPath(context)
        //CGContextMoveToPoint(context, 0, 0)
        //CGContextAddLineToPoint(context, rect.size.width, 0)
        //CGContextStrokePath(context)
        
        //var startAngle : Float = M_PI_2;
        //var endAngle : Float = -M_PI_2
        
        let strokeWidth = 10.0;
        let radius = CGFloat(
            (CGFloat(self.frame.size.width)
                - CGFloat( strokeWidth))/2
                - CGFloat(self.frame.size.width/8)
        )
        
        
        let center = CGPointMake(self.frame.size.width/2, self.frame.height - 10)
        
        CGContextSetStrokeColor(context, [1,0,0,1])
        CGContextSetLineWidth(context, CGFloat(strokeWidth))
        CGContextSetFillColorWithColor(context, UIColor.clearColor().CGColor)
        
        CGContextAddArc(context, center.x, center.y, CGFloat(radius), CGFloat(M_PI), CGFloat(-M_PI*2/3), 0)
        
        CGContextDrawPath(context, kCGPathStroke)
        
        CGContextSetStrokeColor(context2, [1,1,0,1])
        
        CGContextSetLineWidth(context2, CGFloat(strokeWidth))
        CGContextSetFillColorWithColor(context2, UIColor.clearColor().CGColor)
        
        CGContextAddArc(context2, center.x, center.y, CGFloat(radius), CGFloat(-M_PI*2/3), CGFloat(0), 0)
        
        CGContextDrawPath(context2, kCGPathStroke)
        
        
        CGContextSetStrokeColor(context3, [1,0,1,1])
        
        CGContextSetLineWidth(context3, CGFloat(1))
        CGContextSetFillColorWithColor(context3, UIColor.clearColor().CGColor)
        
        CGContextAddArc(context3, center.x, center.y, CGFloat(radius - radius/4), CGFloat(M_PI), CGFloat(0), 0)
        
        CGContextDrawPath(context3, kCGPathStroke)
        
        
        var context4 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context4, [1,1,0,1])
        
        CGContextSetLineWidth(context4, CGFloat(1))
        CGContextSetFillColorWithColor(context4, UIColor.blueColor().CGColor)
        
        CGContextAddArc(context4, center.x, center.y, CGFloat(4), CGFloat(M_PI_2), CGFloat(M_PI_4), 0)
        
        CGContextDrawPath(context4, kCGPathFill)
        
        var context5 = UIGraphicsGetCurrentContext()
        CGContextSetStrokeColor(context5, [1,1,1,1])
        
        CGContextSetLineWidth(context5, CGFloat(1))
        
        CGContextBeginPath(context5)
        CGContextMoveToPoint(context5, center.x, center.y)
        CGContextAddLineToPoint(context5, 5, 5)
        CGContextStrokePath(context5)
        
        
    }

}
