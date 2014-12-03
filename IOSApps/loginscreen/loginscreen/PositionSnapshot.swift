//
//  PositionSnapshot.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/3/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import Foundation

class PositionSnapshot {
    
    var org : String = ""
    var currencyPair : String = ""
    
    var accumulation : Double = 0
    var accumulationInUSD : Double = 0
    var accumulationDisplay : String = ""
    var accumulationInUSDDisplay : String = ""
    
    var realizedPnL : Double = 0
    var realizedPnLInUSD : Double = 0
    var realizedPnLInUSDDisplay : String = ""
    
    var unrealizedPnL : Double = 0
    var unrealizedPnlInUSD : Double = 0
    var unrealizedPnLDisplay : String = ""
    var unrealizedPnLInUSDDisplay : String = ""
    
    var totalPnL : Double = 0
    var totalPnLInUSD : Double = 0
    
    var skewEnabled : Bool = false
    var skewSpread : Double = 0
    
    var avgBuyRate : Double = 0
    var avgSellRate : Double = 0
    
    var baseBuyAmnt : Double = 0
    var baseSellAmnt : Double = 0
    
    var termBuyAmnt : Double = 0
    var termSellAmnt : Double = 0
    
    var valueDate : Int64 = 0
    var lastModifiedTime : Int64 = 0
    var timestamp : Int64 = 0
    
    var bidRate : Double = 0
    var offerRate : Double = 0
    var midRate : Double = 0
    
    var skewedBidRate : Double = 0
    var skewedOfferRate : Double = 0
    
    var volume : Double = 0
    var volumeInUSD : Double = 0
    var volumeInUSDDisplay : String = ""
    
    var yield : Double = 0
    var yieldDisplay : String = ""
    
    var rateProperties : RateProperties = RateProperties()
}