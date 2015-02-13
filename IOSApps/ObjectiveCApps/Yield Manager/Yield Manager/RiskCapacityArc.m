//
//  RiskCapacityArc.m
//  Yield Manager
//
//  Created by Praful Mantale on 2/12/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "RiskCapacityArc.h"

@implementation RiskCapacityArc

int nrp;


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextRef context2 = UIGraphicsGetCurrentContext();
    
    
    CGFloat strokeWidth = 8.0;
    CGFloat radius = (self.frame.size.width - strokeWidth/2 - self.frame.size.width/8)/2.7;
    CGPoint center = CGPointMake(self.frame.size.width/2 , (self.frame.size.height/2) - 4 );
    
    
    CGContextSetStrokeColor(context, CGColorGetComponents( [[UIColor colorWithRed:70.0f/255.0f
                                                                            green:70.0f/255.0f
                                                                             blue:70.0f/255.0f
                                                                            alpha:1.0f] CGColor]));
    
    
    CGContextSetLineWidth(context, strokeWidth);
    CGContextSetFillColorWithColor(context, [[UIColor clearColor]CGColor]);
    
    CGContextAddArc(context, center.x, center.y, radius,  M_PI_2 + M_PI_4, M_PI_4, 0);
    
    CGContextDrawPath(context, kCGPathStroke);
    

    
    
    CGContextSetStrokeColor(context, CGColorGetComponents( [[UIColor colorWithRed:140.0f/255.0f
                                                                            green:179.0f/255.0f
                                                                             blue:226.0f/255.0f
                                                                            alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(context2, strokeWidth);
    CGContextSetFillColorWithColor(context2, [[UIColor clearColor] CGColor]);
    
    
    //2.35 to -785 --> 100
    //135 to 405 --> 270 degrees
    //pi == 180
    //? == X
    //x*pi/180
    float perc = (270 * nrp)/100;
    float endAngle = (perc * M_PI)/180;
    
    
    CGContextAddArc(context2, center.x, center.y, radius, M_PI_2 + M_PI_4, M_PI_2 + M_PI_4 + endAngle, 0);
    
    CGContextDrawPath(context2, kCGPathStroke);
    
    
}

- (void) update:(int)netRiskPercentage{
    
    nrp = netRiskPercentage;
    [self setNeedsDisplay];
    
}

@end
