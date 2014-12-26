//
//  LoginViewController.m
//  Yield Manager
//
//  Created by Praful Mantale on 12/26/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "LoginViewController.h"

@interface LoginViewController ()
@property (weak, nonatomic) IBOutlet UIView *loginPanelView;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
- (IBAction)onTap:(id)sender;

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
@end
