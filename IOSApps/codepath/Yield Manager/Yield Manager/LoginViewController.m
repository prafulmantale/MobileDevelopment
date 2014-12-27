//
//  LoginViewController.m
//  Yield Manager
//
//  Created by Praful Mantale on 12/26/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "LoginViewController.h"
#import "LoginRequest.h"
#import "constants.h"

@interface LoginViewController ()
@property (weak, nonatomic) IBOutlet UIView *loginPanelView;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
- (IBAction)onTap:(id)sender;
- (IBAction)onLogin:(id)sender;

@property (weak, nonatomic) IBOutlet UITextField *orgField;
@property (weak, nonatomic) IBOutlet UITextField *userField;
@property (weak, nonatomic) IBOutlet UITextField *passField;
@property (weak, nonatomic) IBOutlet UISegmentedControl *serverSelector;
- (IBAction)onServerChanged:(id)sender;
@property (weak, nonatomic) IBOutlet UISwitch *rememberMeSwitch;
- (IBAction)onRememberMeSwitched:(id)sender;

-(void)persistLoginDetails;
-(void)restoreLoginDetails;
@end

@implementation LoginViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Do any additional setup after loading the view.
//    
//    self.navigationController.navigationBar.barTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed : @"ym_logo.png"]];
    
    
    self.loginPanelView.layer.cornerRadius = 10;
    self.loginPanelView.layer.shadowOpacity = 0.3;
    self.loginPanelView.layer.shadowRadius = 5;
    self.loginPanelView.layer.shadowOffset = CGSizeMake(-8, 10);
    
    self.loginButton.layer.cornerRadius = 5;
    
    [self restoreLoginDetails];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)onTap:(id)sender {
    [self.view endEditing:true];
    
}

- (IBAction)onLogin:(id)sender {
    
    LoginRequest *loginRequest = [[LoginRequest alloc] init];
    
    loginRequest.organization = self.orgField.text;
    loginRequest.userName = self.userField.text;
    loginRequest.password = self.passField.text;
    
    NSLog(@"JSON : %@", loginRequest.getJSON);
    
    [self persistLoginDetails];
}
- (IBAction)onServerChanged:(id)sender {
}
- (IBAction)onRememberMeSwitched:(id)sender {
}

- (void)viewWillDisappear:(BOOL)animated{
    [self persistLoginDetails];
}

-(void)persistLoginDetails{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    if(self.rememberMeSwitch.on == false){
        [defaults removeObjectForKey:keyLoginOrg];
        [defaults removeObjectForKey:keyLoginUserName];
        [defaults removeObjectForKey:keyLoginPassword];
        return;
    }
    
    [defaults setValue:self.orgField.text forKey:keyLoginOrg];
    [defaults setValue:self.userField.text forKey:keyLoginUserName];
    [defaults setValue:self.passField.text forKey:keyLoginPassword];
    [defaults setInteger: self.serverSelector.selectedSegmentIndex forKey:keyLoginServer];
    [defaults setBool:self.rememberMeSwitch.on forKey:keyRememberMe];
    
}

-(void)restoreLoginDetails{
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    self.orgField.text = [defaults stringForKey:keyLoginOrg];
    self.userField.text = [defaults stringForKey:keyLoginUserName];
    self.passField.text = [defaults stringForKey:keyLoginPassword];
    
    self.serverSelector.selectedSegmentIndex = [defaults integerForKey:keyLoginServer];
    
    self.rememberMeSwitch.on = [defaults boolForKey:keyRememberMe];
}

@end
