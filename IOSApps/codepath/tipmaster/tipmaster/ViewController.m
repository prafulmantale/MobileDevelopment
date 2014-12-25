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
@property (weak, nonatomic) IBOutlet UISegmentedControl *segmentControl;
- (IBAction)onTap:(id)sender;
@property (weak, nonatomic) IBOutlet UILabel *tipPercentageLabel;
@property (weak, nonatomic) IBOutlet UISlider *tipPercentageSlider;
- (IBAction)onTipPercentageChanged:(id)sender;

- (void)updateValues;
- (IBAction)onBillChanged:(id)sender;
- (IBAction)onSettingsClick:(id)sender;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
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
    
    NSArray *tipValues = @[@(0.18),@(0.2),@(0.22)];
    
    float tipAmount = billAmount * [tipValues[self.segmentControl.selectedSegmentIndex] floatValue ];
    
    float newTipAmount = billAmount * self.tipPercentageSlider.value;
    tipAmount = newTipAmount/100;
    
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
@end
