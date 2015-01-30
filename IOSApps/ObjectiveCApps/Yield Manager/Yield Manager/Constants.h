//
//  Constants.h
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#ifndef Yield_Manager_Constants_h
#define Yield_Manager_Constants_h

#define keyLoginOrg @"org"
#define keyLoginUserName @"user"
#define keyLoginPassword @"pass"
#define keyLoginServer @"server"
#define keyRememberMe @"remme"

#define APP_URL @"https://demo2.ym.integral.net"

#define LOGIN_URL @"https://demo2.ym.integral.net/fxi/admin/auth/login"

//Server call to get the org settings e.g. configured currency pairs, policies etc
#define ORG_SETTINGS_URL @"https://demo2.ym.integral.net/fxi/rw/rwadmin/getOrgSettings"

//Makes server request to fetch the rule settings from serve
#define RULES_URL @"https://demo2.ym.integral.net/fxi/rw/rwadmin/getCPRiskRules"

#define REF_DATA_URL @"https://demo2.ym.integral.net/fxi/fxiapi/refdata/supportedCcypairs"

//Get snapshot
#define SNAPSHOT_URL @"https://demo2.ym.integral.net/fxi/rw/warehouse/aggregate?org=YMSBAQA&ccyPairs=ALL&bookingMode=B"

#endif
