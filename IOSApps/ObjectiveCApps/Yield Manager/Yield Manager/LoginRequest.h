//
//  LoginRequest.h
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LoginRequest : NSObject

@property NSString *organization;
@property NSString *userName;
@property NSString *password;

- (NSString *)getJSON;

@end
