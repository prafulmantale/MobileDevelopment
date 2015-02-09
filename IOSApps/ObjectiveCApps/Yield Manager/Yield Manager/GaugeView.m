//
//  GaugeView.m
//  Yield Manager
//
//  Created by Praful Mantale on 2/6/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "GaugeView.h"

@implementation GaugeView


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    
    self.inProfit = true;
    
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextRef context2 = UIGraphicsGetCurrentContext();
    CGContextRef context3 = UIGraphicsGetCurrentContext();
    
    //CGContextSetStrokeColor(context, [1,0,0,1])
    //CGContextBeginPath(context)
    //CGContextMoveToPoint(context, 0, 0)
    //CGContextAddLineToPoint(context, rect.size.width, 0)
    //CGContextStrokePath(context)
    
    //var startAngle : Float = M_PI_2;
    //var endAngle : Float = -M_PI_2
    
    CGFloat strokeWidth = 10.0;
    CGFloat radius = (self.frame.size.width - strokeWidth/2 - self.frame.size.width/8)/3;
    CGPoint center = CGPointMake(self.frame.size.width/2, self.frame.size.height - 10);
    
    //First Half
    if(self.inProfit){
        
        CGContextSetStrokeColor(context, CGColorGetComponents( [[UIColor colorWithRed:213.0f/255.0f
                                                                                green:213.0f/255.0f
                                                                                 blue:213.0f/255.0f
                                                                                alpha:1.0f] CGColor]));
    }
    else{
        CGContextSetStrokeColor(context, CGColorGetComponents( [[UIColor colorWithRed:222.0f/255.0f
                                                                                green:0.0f/255.0f
                                                                                 blue:3.0f/255.0f
                                                                                alpha:1.0f] CGColor]));
    }
    
    CGContextSetLineWidth(context, strokeWidth);
    CGContextSetFillColorWithColor(context, [[UIColor clearColor]CGColor]);
    
    CGContextAddArc(context, center.x, center.y, radius, M_PI, -M_PI*2/3, 0);
    
    CGContextDrawPath(context, kCGPathStroke);
    
    
    
    //Second half
    if(self.inProfit){
        CGContextSetStrokeColor(context2, CGColorGetComponents( [[UIColor colorWithRed:46.0f/255.0f
                                                                                 green:99.0f/255.0f
                                                                                  blue:170.0f/255.0f
                                                                                 alpha:1.0f] CGColor]));
        
    }
    else{
    CGContextSetStrokeColor(context2, CGColorGetComponents( [[UIColor colorWithRed:213.0f/255.0f
                                                                             green:213.0f/255.0f
                                                                              blue:213.0f/255.0f
                                                                             alpha:1.0f] CGColor]));
    }
    
    CGContextSetLineWidth(context2, strokeWidth);
    CGContextSetFillColorWithColor(context2, [[UIColor clearColor] CGColor]);
    
    CGContextAddArc(context2, center.x, center.y, radius, -M_PI*2/3, 0, 0);
    
    CGContextDrawPath(context2, kCGPathStroke);
    
    
    //Inner arc
    
    CGContextSetStrokeColor(context3, CGColorGetComponents( [[UIColor colorWithRed:192.0f/255.0f
                                                      green:208.0f/255.0f
                                                       blue:224.0f/255.0f
                                                      alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(context3, 1);
    CGContextSetFillColorWithColor(context3, [[UIColor clearColor] CGColor]);
    
    CGContextAddArc(context3, center.x, center.y, radius - radius/4, M_PI, 0, 0);
    
    CGContextDrawPath(context3, kCGPathStroke);
    
    //Needle end circle
    CGContextRef context4 = UIGraphicsGetCurrentContext();
    CGContextSetStrokeColor(context4, CGColorGetComponents( [[UIColor colorWithRed:100.0f/255.0f
                                                                             green:100.0f/255.0f
                                                                              blue:100.0f/255.0f
                                                                             alpha:1.0f] CGColor]));

    
    CGContextSetLineWidth(context4, 1);
    CGContextSetFillColorWithColor(context4, [[UIColor colorWithRed:100.0f/255.0f
                                                              green:100.0f/255.0f
                                                               blue:100.0f/255.0f
                                                              alpha:1.0f] CGColor]);
    
    CGContextAddArc(context4, center.x, center.y, 4, M_PI_2, M_PI_4, 0);
    
    CGContextDrawPath(context4, kCGPathFill);
    
    
    //Needle
    CGContextRef context5 = UIGraphicsGetCurrentContext();
    CGContextSetStrokeColor(context5, CGColorGetComponents( [[UIColor colorWithRed:100.0f/255.0f
                                                                             green:100.0f/255.0f
                                                                              blue:100.0f/255.0f
                                                                             alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(context5, 1);
    
    CGContextBeginPath(context5);
    CGContextMoveToPoint(context5, center.x, center.y);
    
    float angle = 90 * M_PI/ 180;
    CGFloat endX   = center.x - 3*radius/4 * sin(angle);//sin(newangle);
    CGFloat endY   = center.y + 3*radius/4 * cos(angle);//cos(newangle);
    
    NSLog(@"%f,%f,%f", angle, endX,endY );
    
    CGContextAddLineToPoint(context5, endX, endY);
    CGContextStrokePath(context5);
}


@end
