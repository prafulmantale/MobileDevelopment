//
//  PositionsViewCell.h
//  Yield Manager
//
//  Created by Praful Mantale on 2/6/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PositionStatusView.h"

@interface PositionsViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIView *rateContainerPanel;

@property (weak, nonatomic) IBOutlet UIView *rateInnerPanel;

@property (weak, nonatomic) IBOutlet UILabel *ccyPairLabel;

@property (weak, nonatomic) IBOutlet UILabel *rateLabel;

@property (weak, nonatomic) IBOutlet UILabel *maxShortLabel;

@property (weak, nonatomic) IBOutlet UILabel *maxLongLabel;

@property (weak, nonatomic) IBOutlet UILabel *lossThresholdLabel;

@property (weak, nonatomic) IBOutlet UILabel *profitThresholdLabel;

@end
