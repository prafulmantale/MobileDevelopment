//
//  LoginRequest.h
//  Yield Manager
//
//  Created by Praful Mantale on 12/26/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LoginRequest : NSObject{
    
}

@property NSString *organization;
@property NSString *userName;
@property NSString *password;

- (NSString *)getJSON;

@end
