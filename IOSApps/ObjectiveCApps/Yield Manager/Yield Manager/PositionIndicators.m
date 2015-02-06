//
//  PositionIndicators.m
//  Yield Manager
//
//  Created by Praful Mantale on 2/4/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "PositionIndicators.h"

@implementation PositionIndicators

-(void)update: (int)maxCount  currentPos: (int)currentPos maxPos:(double)maxPos{
    
    double percentage =  maxCount * currentPos/maxPos;
    
    percentage = abs(percentage);
    
    int filledCount = (int)percentage;
    
    
    
    if(filledCount < 0){
        filledCount = 0;
    }
    
    if(filledCount > maxCount){
        filledCount = (int)maxCount;
    }
    
    self.blankCount = maxCount - filledCount;
    
    if(filledCount == 0){
        
        self.normalCount = 0;
        self.warningCount = 0;
        self.dangerCount = 0;
    }
    else if(filledCount <= 17){
        
        self.normalCount = filledCount;
        self.warningCount = 0;
        self.dangerCount = 0;
    }
    else if(filledCount < 33){
        
        self.normalCount = 17;
        self.warningCount = 34 - self.blankCount - self.normalCount;
        self.dangerCount = 0;
    }
    else{
        
        self.normalCount = 17;
        self.warningCount = 15;
        self.dangerCount = 34 - self.blankCount - self.normalCount - self.warningCount;
    }
}

@end
