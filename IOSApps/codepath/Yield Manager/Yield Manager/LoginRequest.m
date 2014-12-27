//
//  LoginRequest.m
//  Yield Manager
//
//  Created by Praful Mantale on 12/26/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "LoginRequest.h"
#import "constants.h"

@implementation LoginRequest{
    
    
    
}

- (NSString *)getJSON{
    
    NSString *jsonStr =[NSString stringWithFormat:@"{\"%@\":\"%@\",\"%@\":\"%@\",\"%@\":\"%@\"}",keyLoginOrg,  self.organization, keyLoginUserName, self.userName, keyLoginPassword, self.password];

    
    return jsonStr;
}

@end
