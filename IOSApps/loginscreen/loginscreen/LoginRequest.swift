//
//  LoginRequest.swift
//  loginscreen
//
//  Created by Praful Mantale on 12/3/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

import Foundation

class LoginRequest{
    
    let orgKey : String = "org"
    let userKey : String = "user"
    let passwordKey : String = "pass"
    
    var orgName : String!
    var userName : String!
    var password : String!
    
    init(orgName : String, userName : String, password : String){
        self.orgName = orgName;
        self.userName = userName
        self.password = password
    }
    
    func getJsonString()->String{
        
        //"{\"org\":\"YMSBADemo\",\"user\":\"RWPraful\",\"pass\":\"Test123\"}";
        return "{\"\(orgKey)\":\"\(orgName)\",\"\(userKey)\":\"\(userName)\",\"\(passwordKey)\":\"\(password)\"}"
    }
    
}
