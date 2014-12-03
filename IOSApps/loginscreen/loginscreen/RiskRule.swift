//
//  RiskRule.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/3/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import Foundation

class RiskRule{
    

    
//    private static final DecimalFormat thresholdFormat = new DecimalFormat("0")
    
    var currencyPair : String = ""
    var active : Bool = false
    var maxLimitShort : Double = 0
    var maxLimitLong : Double = 0
    var profitThreshold : Double = 0
    var lossThreshold : Double = 0
    var maxShortInThousands : Int = 0
    var maxLongInThousands : Int = 0
    var maxShortInThousandsStr : String = ""
    var maxLongInThousandsStr : String = ""
    var lossThresholdStr : String = ""
    var profitThresholdStr : String = ""
}
