//
//  SettingsViewController.m
//  tipduggu
//
//  Created by Praful Mantale on 12/30/14.
//  Copyright (c) 2014 prafulkumarmantale. All rights reserved.
//

#import "SettingsViewController.h"
#import "constants.h"

@interface SettingsViewController ()

@property (weak, nonatomic) IBOutlet UISlider *excellentSlider;

- (IBAction)onExcellentSettingChanged:(id)sender;
@property (weak, nonatomic) IBOutlet UILabel *excellentSettingsLabel;

@property (weak, nonatomic) IBOutlet UISlider *satisfactorySlider;

- (IBAction)onSatisfactorySettingChanged:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *satisfactorySettingsLabel;

@property (weak, nonatomic) IBOutlet UISlider *badSlider;

- (IBAction)onBadSettingsChanged:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *badSettingsLabel;

- (void)updateValues;

- (void)persistSettings;
- (void)loadSettings;

@end

@implementation SettingsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
     self.view.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"bg_gr.jpg"]];
    
    [self loadSettings];
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

- (void)viewWillDisappear:(BOOL)animated{
    
    [self persistSettings];
}

- (void)persistSettings{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    [defaults setInteger:self.excellentSlider.value forKey: keyExcellentServiceTip];
    
    [defaults setInteger:self.satisfactorySlider.value forKey:keySatisfactoryServiceTip];
    [defaults setInteger:self.badSlider.value forKey:keyBadServiceTip];
    
    [defaults synchronize];//Flush changes to disk
}

- (void)loadSettings{
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    if(defaults != nil){
        
        self.excellentSlider.value =
        [defaults integerForKey:keyExcellentServiceTip];
        
        self.satisfactorySlider.value =
        [defaults integerForKey:keySatisfactoryServiceTip];
        
        self.badSlider.value =
        [defaults integerForKey:keyBadServiceTip];
    }
}

@end
