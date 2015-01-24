//
//  Summary.h
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Summary : NSObject

//    summary: {nrp: 21, yld: 14.506457304, vol: 226508094.37, rpnl: 3301.27, upnl: -15.44, pnl: 3285.83,…}
//    bookingMode: "B"
//    nrp: 21
//    pnl: 3285.83
//    rpnl: 3301.27
//    tacc: 167356.53
//    tlim: 800000
//    upnl: -15.44
//    vol: 226508094.37
//    yld: 14.506457304


@property NSString *bookingMode;
@property double netRiskPercentage;
@property NSString *netRiskPercentageDisplay;
@property double profitAndLoss;
@property double realizedProfitAndLoss;
@property NSString *realizedProfitAndLossDisplay;
@property double totalAccumulation;
@property double thresholdLimit;
@property double unrealizedProfitAndLoss;
@property NSString *unrealizedProfitAndLossDisplay;
@property double volume;
@property NSString *volumeDisplay;
@property double yieldPerMillion;
@property NSString *yieldPerMillionDisplay;






@end
