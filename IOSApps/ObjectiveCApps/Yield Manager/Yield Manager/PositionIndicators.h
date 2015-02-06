//
//  PositionIndicators.h
//  Yield Manager
//
//  Created by Praful Mantale on 2/4/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface PositionIndicators : NSObject

@property int blankCount;
@property int normalCount;
@property int warningCount;
@property int dangerCount;

- (void)update:(int)maxCount currentPos: (int)currentPos maxPos:(double)maxPos;


@end
