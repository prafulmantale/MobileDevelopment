//
//  PositionsViewCell.m
//  Yield Manager
//
//  Created by Praful Mantale on 2/6/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "PositionsViewCell.h"
#import "GaugeView.h"

@implementation PositionsViewCell

- (void)awakeFromNib {
    // Initialization code
    
    self.rateContainerPanel.layer.cornerRadius = 5;
    self.rateInnerPanel.layer.cornerRadius = 5;
    
    PositionStatusView *statusView = [[PositionStatusView alloc] init];
    statusView.frame = CGRectMake(120, 35, 300, 30);
    statusView.backgroundColor = [UIColor clearColor];
    [self addSubview:statusView];
    [self bringSubviewToFront:statusView];
    
    GaugeView *gaugeView = [[GaugeView alloc] init];
    gaugeView.frame = CGRectMake(420, 10, 150, 70);
    gaugeView.backgroundColor = [UIColor clearColor];
    [self addSubview:gaugeView];
    [self bringSubviewToFront:gaugeView];
    
    
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
