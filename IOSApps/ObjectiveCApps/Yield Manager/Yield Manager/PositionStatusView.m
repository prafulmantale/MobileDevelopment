//
//  PositionStatusView.m
//  Yield Manager
//
//  Created by Praful Mantale on 2/4/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "PositionStatusView.h"
#import "PositionIndicators.h"

@implementation PositionStatusView

CGFloat skip = 1;
CGFloat strokeWidth = 3;
CGFloat startX = 19;
CGFloat barHt = 14;

bool contextsInitialized = false;


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
//     Drawing code
    
    if(contextsInitialized == false){
        
        self.shortPositions = [[PositionIndicators alloc] init];
        self.longPositions = [[PositionIndicators alloc] init];

        [self.shortPositions update:34 currentPos:0 maxPos:100];
        [self.longPositions update:34 currentPos:0 maxPos:100];
        
        contextsInitialized = true;
    }
    
    
    CGFloat fvl = startX - strokeWidth - 0.5;
    
    [self drawVerticalLine:fvl];
    
    //drawIndicators()
    //drawVerticalLine(startX - 0.75)
    
    [self drawIndicators];
    [self drawVerticalLine:startX - 0.5];
    [self drawVerticalLine:(fvl + startX - 0.5)/2];
    [self drawVerticalLine:(fvl + startX - 0.5)/4];
    [self drawVerticalLine:(fvl + startX - 0.5) * 3/4 ];
    [self drawDottedLines];
}

-(void) drawDottedLines{
    CGContextRef context9 = UIGraphicsGetCurrentContext();
    float dashPhase = 0.0;
    CGFloat dashLengths[] = {4, 2};
    CGContextSetRGBStrokeColor(context9, 0,0,0,1);
    CGContextSetLineDash(context9, dashPhase, dashLengths, 2);
    
    CGContextSetLineWidth(context9, 0.5);
    CGContextMoveToPoint(context9,  19 - strokeWidth, 20);
    CGContextSetLineCap(context9, kCGLineCapRound);
    CGContextAddLineToPoint(context9, startX, 20);
    
    CGContextStrokePath(context9);
}

-(void) drawVerticalLine: (CGFloat)startX{
    CGFloat centerHt = 27;
    CGContextRef context9 = UIGraphicsGetCurrentContext();
    CGContextSetRGBStrokeColor(context9, 0,0,0,1);
    
    CGContextSetLineWidth(context9, 0.5);
    CGContextMoveToPoint(context9,  startX, 0);
    CGContextAddLineToPoint(context9, startX, centerHt);
    CGContextStrokePath(context9);
}

-(void) drawIndicators{
    
    CGContextRef contextNormal = UIGraphicsGetCurrentContext();
    CGContextRef contextBlank = UIGraphicsGetCurrentContext();
    

    
    CGContextSetStrokeColor(contextBlank, CGColorGetComponents( [[UIColor colorWithRed:213.0f/255.0f
                                                                             green:213.0f/255.0f
                                                                              blue:213.0f/255.0f
                                                                             alpha:1.0f] CGColor]));
    CGContextSetLineWidth(contextBlank, strokeWidth);
    
    if(self.shortPositions.blankCount > 0){
        unsigned int i;
        unsigned int cnt = self.shortPositions.blankCount;
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextBlank);
            CGContextMoveToPoint(contextBlank,  startX, 0);
            CGContextAddLineToPoint(contextBlank, startX, barHt);
            CGContextStrokePath(contextBlank);
            
            startX = startX + strokeWidth + skip;
            
        }
    }
    
    CGContextRef contextDanger = UIGraphicsGetCurrentContext();
    
    CGContextSetStrokeColor(contextDanger, CGColorGetComponents( [[UIColor colorWithRed:193.0f/255.0f
                                                                                     green:30.0f/255.0f
                                                                                      blue:0.0f/255.0f
                                                                                     alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(contextDanger, strokeWidth);
    
    if(self.shortPositions.dangerCount > 0) {
        unsigned int i;
        unsigned int cnt = self.shortPositions.dangerCount;
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextDanger);
            CGContextMoveToPoint(contextDanger,  startX, 0);
            CGContextAddLineToPoint(contextDanger, startX, barHt);
            CGContextStrokePath(contextDanger);
            
            startX = startX + strokeWidth + skip;
            
        }
    }
    
    CGContextRef contextWarning = UIGraphicsGetCurrentContext();
    
    CGContextSetStrokeColor(contextWarning, CGColorGetComponents( [[UIColor colorWithRed:255.0f/255.0f
                                                                                   green:135.0f/255.0f
                                                                                    blue:0.0f/255.0f
                                                                                   alpha:1.0f] CGColor]));
    
    
    CGContextSetLineWidth(contextWarning, strokeWidth);
    
    if(self.shortPositions.warningCount > 0 ){
        unsigned int i;
        unsigned int cnt = self.shortPositions.warningCount;
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextWarning);
            CGContextMoveToPoint(contextWarning,  startX, 0);
            CGContextAddLineToPoint(contextWarning, startX, barHt);
            CGContextStrokePath(contextWarning);
            
            startX = startX + strokeWidth + skip;
            
        }
    }
    
    CGContextSetStrokeColor(contextNormal, CGColorGetComponents( [[UIColor colorWithRed:37.0f/255.0f
                                                                                  green:75.0f/255.0f
                                                                                   blue:196.0f/255.0f
                                                                                  alpha:1.0f] CGColor]));
    CGContextSetLineWidth(contextNormal, strokeWidth);
    
    if(self.shortPositions.normalCount + self.longPositions.normalCount > 0 ){
        
        unsigned int i;
        unsigned int cnt = self.shortPositions.normalCount + self.longPositions.normalCount;
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextNormal);
            CGContextMoveToPoint(contextNormal,  startX, 0);
            CGContextAddLineToPoint(contextNormal, startX, barHt);
            CGContextStrokePath(contextNormal);
            
            startX = startX + strokeWidth + skip;
        }
    }
    
    CGContextSetStrokeColor(contextWarning, CGColorGetComponents( [[UIColor colorWithRed:255.0f/255.0f
                                                                                   green:135.0f/255.0f
                                                                                    blue:0.0f/255.0f
                                                                                   alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(contextWarning, strokeWidth);
    
    if(self.longPositions.warningCount > 0 ){
        
        unsigned int i;
        unsigned int cnt = self.longPositions.warningCount;
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextWarning);
            CGContextMoveToPoint(contextWarning,  startX, 0);
            CGContextAddLineToPoint(contextWarning, startX, barHt);
            CGContextStrokePath(contextWarning);
            
            startX = startX + strokeWidth + skip;
        }
    }
    
    CGContextSetStrokeColor(contextDanger, CGColorGetComponents( [[UIColor colorWithRed:193.0f/255.0f
                                                                                  green:30.0f/255.0f
                                                                                   blue:0.0f/255.0f
                                                                                  alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(contextDanger, strokeWidth);
    
    if(self.longPositions.dangerCount > 0 ){
        unsigned int i;
        unsigned int cnt = self.longPositions.dangerCount;
        
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextDanger);
            CGContextMoveToPoint(contextDanger,  startX, 0);
            CGContextAddLineToPoint(contextDanger, startX, barHt);
            CGContextStrokePath(contextDanger);
            
            startX = startX + strokeWidth + skip;
        }
    }
    
    
    CGContextSetStrokeColor(contextBlank, CGColorGetComponents( [[UIColor colorWithRed:213.0f/255.0f
                                                                                 green:213.0f/255.0f
                                                                                  blue:213.0f/255.0f
                                                                                 alpha:1.0f] CGColor]));
    
    CGContextSetLineWidth(contextBlank, strokeWidth);
    
    if(self.longPositions.blankCount > 0){
        unsigned int i;
        unsigned int cnt = self.longPositions.blankCount;
        
        for (i = 0; i < cnt;  i++){
            CGContextBeginPath(contextBlank);
            CGContextMoveToPoint(contextBlank,  startX, 0);
            CGContextAddLineToPoint(contextBlank, startX, barHt);
            CGContextStrokePath(contextBlank);
            
            startX = startX + strokeWidth + skip;
            
        }
    }
}


-(void) setCurrentPosition: (double)currentPosition{
    
    if(currentPosition < 0){
        [self.shortPositions update:34 currentPos:currentPosition maxPos:100];
        [self.longPositions update:34 currentPos:0 maxPos:100];
        
    }
    else{
        [self.shortPositions update:34 currentPos:0 maxPos:100];
        [self.longPositions update:34 currentPos:currentPosition maxPos:100];
    }
    
    startX = 19;
    [self setNeedsDisplay];
}


@end
