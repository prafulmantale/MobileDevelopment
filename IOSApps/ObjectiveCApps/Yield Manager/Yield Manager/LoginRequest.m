//
//  LoginRequest.m
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "LoginRequest.h"
#import "Constants.h"

@implementation LoginRequest{
    
}

- (NSString *)getJSON{
    
    NSString *jsonStr =[NSString stringWithFormat:@"{\"%@\":\"%@\",\"%@\":\"%@\",\"%@\":\"%@\"}",keyLoginOrg,  self.organization, keyLoginUserName, self.userName, keyLoginPassword, self.password];
    
    
    return jsonStr;
}

@end
