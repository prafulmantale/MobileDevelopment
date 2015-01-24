//
//  ViewController.m
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "ViewController.h"
#import "LoginRequest.h"
#import "Constants.h"

@interface ViewController ()

@property (weak, nonatomic) IBOutlet UIButton *btnLogin;

- (IBAction)onLogin:(id)sender;


@property (weak, nonatomic) IBOutlet UIView *loginPanel;


@property (strong, nonatomic) IBOutlet UITapGestureRecognizer *onTap;

- (IBAction)onTap:(id)sender;

@property (weak, nonatomic) IBOutlet UITextField *orgField;

@property (weak, nonatomic) IBOutlet UITextField *userField;

@property (weak, nonatomic) IBOutlet UITextField *passField;

-(void)persistLoginDetails;
-(void)restoreLoginDetails;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
   
    
    self.loginPanel.layer.cornerRadius = 10;
    self.loginPanel.layer.shadowOpacity = 0.15;
    self.loginPanel.layer.shadowRadius = 10;
    self.loginPanel.layer.shadowOffset = CGSizeMake(-30, 30);
    
    
    self.btnLogin.layer.cornerRadius = 5;
    
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)onLogin:(id)sender {
    
    LoginRequest *loginRequest = [[LoginRequest alloc] init];
    
    loginRequest.organization = self.orgField.text;
    loginRequest.userName = self.userField.text;
    loginRequest.password = self.passField.text;
    
    NSLog(@"JSON : %@", loginRequest.getJSON);
    
    
}
- (IBAction)onTap:(id)sender {
    
    [self.view endEditing:true];
}


-(void)persistLoginDetails{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
//    if(self.rememberMeSwitch.on == false){
//        [defaults removeObjectForKey:keyLoginOrg];
//        [defaults removeObjectForKey:keyLoginUserName];
//        [defaults removeObjectForKey:keyLoginPassword];
//        return;
//    }
    
    [defaults setValue:self.orgField.text forKey:keyLoginOrg];
    [defaults setValue:self.userField.text forKey:keyLoginUserName];
    [defaults setValue:self.passField.text forKey:keyLoginPassword];
//    [defaults setInteger: self.serverSelector.selectedSegmentIndex forKey:keyLoginServer];
//    [defaults setBool:self.rememberMeSwitch.on forKey:keyRememberMe];
    
}

-(void)restoreLoginDetails{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    self.orgField.text = [defaults stringForKey:keyLoginOrg];
    self.userField.text = [defaults stringForKey:keyLoginUserName];
    self.passField.text = [defaults stringForKey:keyLoginPassword];
    
//    self.serverSelector.selectedSegmentIndex = [defaults integerForKey:keyLoginServer];
//    
//    self.rememberMeSwitch.on = [defaults boolForKey:keyRememberMe];
}
@end
