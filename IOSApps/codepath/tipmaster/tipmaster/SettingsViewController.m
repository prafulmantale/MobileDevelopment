//
//  SettingsViewController.m
//  tipmaster
//
//  Created by Praful Mantale on 12/24/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "SettingsViewController.h"

@interface SettingsViewController ()
@property (weak, nonatomic) IBOutlet UIView *excellentView;
@property (weak, nonatomic) IBOutlet UIView *satsfactoryView;
@property (weak, nonatomic) IBOutlet UIView *wtfView;

@property (weak, nonatomic) IBOutlet UISlider *excellentSlider;

- (IBAction)onExcellentSettingChanged:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *excellentSettingsLabel;

@property (weak, nonatomic) IBOutlet UISlider *satisfactorySlider;

- (IBAction)onSatisfactorySettingChanged:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *satisfactorySettingsLabel;
@property (weak, nonatomic) IBOutlet UISlider *badSlider;
- (IBAction)onBadSettingsChanged:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *badSettingsLabel;

-(void)updateValues;

@end

@implementation SettingsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
    
//    self.excellentView.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
//    
//    self.satsfactoryView.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
//    
//    self.wtfView.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
    
    [self updateValues];
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

- (IBAction)onExcellentSettingChanged:(id)sender {
    self.excellentSlider.value = (int)self.excellentSlider.value;
    [self updateValues];
}
- (IBAction)onSatisfactorySettingChanged:(id)sender {
    self.satisfactorySlider.value = (int)self.satisfactorySlider.value;
    [self updateValues];
}
- (IBAction)onBadSettingsChanged:(id)sender {
    self.badSlider.value = (int)self.badSlider.value;
    [self updateValues];
}

- (void)updateValues{
    
    self.excellentSettingsLabel.text = [[NSString stringWithFormat:@"%.0f", self.excellentSlider.value] stringByAppendingString:@"%"];
    
    self.satisfactorySettingsLabel.text = [[NSString stringWithFormat:@"%.0f", self.satisfactorySlider.value] stringByAppendingString:@"%"];
    
    self.badSettingsLabel.text = [[NSString stringWithFormat:@"%0.0f", self.badSlider.value] stringByAppendingString: @"%"];
    
}
@end
