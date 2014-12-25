//
//  ViewController.m
//  tipmaster
//
//  Created by Praful Mantale on 12/24/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "ViewController.h"
#import "SettingsViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UITextField *tipAmountField;
@property (weak, nonatomic) IBOutlet UILabel *tipAmountLabel;
@property (weak, nonatomic) IBOutlet UILabel *totalAmountLabel;

- (IBAction)onTap:(id)sender;
@property (weak, nonatomic) IBOutlet UILabel *tipPercentageLabel;
@property (weak, nonatomic) IBOutlet UISlider *tipPercentageSlider;
- (IBAction)onTipPercentageChanged:(id)sender;

- (void)updateValues;
- (IBAction)onBillChanged:(id)sender;
- (IBAction)onSettingsClick:(id)sender;
@property (weak, nonatomic) IBOutlet UISlider *splitSlider;


- (IBAction)onSplitChanged:(id)sender;
- (void)updateSplitValues;
@property (weak, nonatomic) IBOutlet UIView *splitView;
@property (weak, nonatomic) IBOutlet UIView *tipView;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
    
    
    //self.splitView.layer.cornerRadius = 5;
    self.splitView.clipsToBounds = true;
    self.splitView.layer.masksToBounds = false;
    self.splitView.layer.shadowRadius = 5;
    self.splitView.layer.shadowOpacity = 0.3;
    self.splitView.layer.shadowOffset = CGSizeMake(-8, 10);
//    self.splitView.layer.borderColor = [UIColor whiteColor].CGColor;
//    self.splitView.layer.borderWidth = 0.0f;
    
    //self.tipView.layer.cornerRadius = 8;
    self.tipView.clipsToBounds = true;
    self.tipView.layer.masksToBounds = false;
    self.tipView.layer.shadowRadius = 5;
    self.tipView.layer.shadowOpacity = 0.3;
    self.tipView.layer.shadowOffset = CGSizeMake(-8, 10);
//    self.tipView.layer.borderWidth = 0.0f;
//    self.tipView.layer.borderColor = [UIColor whiteColor].CGColor;

    
    [self updateValues];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)onTap:(id)sender {
    [self.view endEditing:YES];
    [self updateValues];
}

- (IBAction)onTipPercentageChanged:(id)sender {
    
    UISlider *slider = sender;
    
    slider.value = (int)slider.value;
    
    [self updateValues];
    
}

- (void)updateValues{
    float billAmount = [self.tipAmountField.text floatValue];
    
    float tipAmount = billAmount * self.tipPercentageSlider.value;
    tipAmount = tipAmount/100;
    
    NSString *percLabel = [@"(" stringByAppendingString: [NSString stringWithFormat:@"%.0f", self.tipPercentageSlider.value]];
    
    percLabel = [percLabel stringByAppendingString:@"%"];
    percLabel = [percLabel stringByAppendingString:@")"];
    
    self.tipPercentageLabel.text = percLabel;
    
    float totalAmount = billAmount + tipAmount;
    
    self.tipAmountLabel.text = [NSString stringWithFormat:@"$%0.2f", tipAmount];
    
    self.totalAmountLabel.text = [NSString stringWithFormat:@"$%0.2f", totalAmount];
}

- (IBAction)onBillChanged:(id)sender {
    [self updateValues];
}

- (IBAction)onSettingsClick:(id)sender {
    [self.navigationController pushViewController:[[SettingsViewController alloc] init] animated:YES];
}
- (IBAction)onSplitChanged:(id)sender {
    UISlider *slider = sender;
    slider.value = (int)slider.value;
    
    [self updateSplitValues];
}

- (void)updateSplitValues{
    
}
@end
