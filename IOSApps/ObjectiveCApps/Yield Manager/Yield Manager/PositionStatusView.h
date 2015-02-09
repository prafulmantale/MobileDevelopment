//
//  PositionStatusView.h
//  Yield Manager
//
//  Created by Praful Mantale on 2/4/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PositionIndicators.h"

@interface PositionStatusView : UIView


@property PositionIndicators *shortPositions;
@property PositionIndicators *longPositions;

-(void) setCurrentPosition: (double)currentPosition;

@end
