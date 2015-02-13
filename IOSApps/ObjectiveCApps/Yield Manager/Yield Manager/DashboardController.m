//
//  DashboardController.m
//  Yield Manager
//
//  Created by Praful Mantale on 1/23/15.
//  Copyright (c) 2015 prafulkumarmantale. All rights reserved.
//

#import "DashboardController.h"
#import "Constants.h"
#import "Summary.h"
#import "Snapshot.h"
#import "PositionsViewCell.h"
#import "RiskCapacityArc.h"

@interface DashboardController ()
@property (weak, nonatomic) IBOutlet UITableView *positionsTableView;

-(void)getRWSnapShot;

@property (nonatomic, strong) NSArray *snapshots;

@property (weak, nonatomic) IBOutlet UILabel *yieldLabel;

@property (weak, nonatomic) IBOutlet UILabel *volumeLabel;

@property (weak, nonatomic) IBOutlet UILabel *realizedPnLLabel;

@property (weak, nonatomic) IBOutlet UILabel *unrealizedPnLLabel;


@property (weak, nonatomic) IBOutlet UIView *riskCapacityView;

@property (weak, nonatomic) IBOutlet UILabel *netRiskPercentageLabel;

@end

@implementation DashboardController
RiskCapacityArc *riskArc ;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    riskArc = [[RiskCapacityArc alloc] init];
    riskArc.frame = CGRectMake(0, 0, 134, 114);
    riskArc.backgroundColor = [UIColor clearColor];
    [self.riskCapacityView addSubview:riskArc];
    [self.riskCapacityView bringSubviewToFront:riskArc];
    
    
    self.positionsTableView.tableHeaderView = nil;
    self.positionsTableView.tableFooterView = nil;
    
//    self.positionsTableView.layer.borderWidth = 1;
//    self.positionsTableView.layer.borderColor = [UIColor blueColor].CGColor;
    
    self.positionsTableView.dataSource = self;
    
    self.positionsTableView.rowHeight = 100;
    
    [self.positionsTableView registerNib:[UINib nibWithNibName:@"PositionsViewCell" bundle:nil] forCellReuseIdentifier:@"PositionCell"];
    
  
    
    [self getRWSnapShot];
    
    [self startTimedTask];
    
}

- (void)startTimedTask
{
    NSTimer *fiveSecondTimer = [NSTimer scheduledTimerWithTimeInterval:2.0 target:self selector:@selector(getRWSnapShot) userInfo:nil repeats:YES];
}


- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 550.0;
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


-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.snapshots.count;
}



- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    PositionsViewCell *pCell = [tableView dequeueReusableCellWithIdentifier:@"PositionCell"];
    
    //UITableViewCell *cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:nil];
    
    NSDictionary *snapshot = self.snapshots[indexPath.row];
    
    pCell.ccyPairLabel.text = snapshot[@"ccyPair"];
    
    
    pCell.rateLabel.text = [NSString stringWithFormat:@"%0.5f", [snapshot[@"midRate"] doubleValue]];
    
    NSString *rate = @"";
    NSRange range = [pCell.ccyPairLabel.text rangeOfString:@"JPY"];
    
    if(range.location == NSNotFound){
        rate = [NSString stringWithFormat:@"%0.5f", [snapshot[@"midRate"] doubleValue]];
    }
    else{
        rate = [NSString stringWithFormat:@"%0.3f", [snapshot[@"midRate"] doubleValue]];
    }
    
    
    
    UIFont *fnt = [UIFont fontWithName:@"Helvetica" size:14.0];
    
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:rate
                                                                                         attributes:@{NSFontAttributeName: [fnt fontWithSize:14]}];
    [attributedString setAttributes:@{NSFontAttributeName : [fnt fontWithSize:20]
                                      , NSBaselineOffsetAttributeName : @0} range:NSMakeRange(rate.length - 3, 2)];
    
    [attributedString setAttributes:@{NSFontAttributeName : [fnt fontWithSize:12]
                                      , NSBaselineOffsetAttributeName : @10} range:NSMakeRange(rate.length - 1, 1)];
    
    pCell.rateLabel.attributedText = attributedString;
    
    pCell.maxShortLabel.text = @"(100)";
    pCell.maxLongLabel.text = @"100";
    
    pCell.lossThresholdLabel.text = @"(500)";
    pCell.profitThresholdLabel.text = @"500";

    
    return pCell;
}


-(void) getRWSnapShot{
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:SNAPSHOT_URL] cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:5];
    
    [request setValue:@"application/json"  forHTTPHeaderField:@"Content-Type"];
    
    request.HTTPShouldHandleCookies = true;
    
    [NSURLConnection sendAsynchronousRequest:request queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse *response, NSData *data, NSError *connectionError) {
        id object = [NSJSONSerialization JSONObjectWithData:data options:0 error:nil];
        
        NSLog(@"%@", object);
        
        NSString *status = [NSString alloc];
        status = object[@"status"];
        NSLog(@"Status : %@", status);
        if([status  isEqual: @"OK"]){
            
            self.snapshots = object[@"warehouseSnapshots"];
            
            id summ = object[@"summary"];
            Summary *summary = [[Summary alloc] init];
            summary.bookingMode = summ[@""];
            
            summary.bookingMode = summ[@"bookingMode"];
            
            summary.netRiskPercentage = [summ[@"nrp"] floatValue];
            
            summary.profitAndLoss = [summ[@"pnl"] floatValue];
            
            summary.realizedProfitAndLoss = [summ[@"rpnl"] floatValue];
            summary.totalAccumulation = [summ[@"tacc"] floatValue];
            summary.thresholdLimit = [summ[@"tlim"] floatValue];
            summary.unrealizedProfitAndLoss = [summ[@"upnl"] floatValue];
            summary.volume = [summ[@"vol"] floatValue];
            summary.yieldPerMillion = [summ[@"yld"] floatValue];
            
//            loadSnapshots(snapshots);
            
            self.yieldLabel.text = [NSString stringWithFormat:@"%.0f", summary.yieldPerMillion];
            
            
            
            NSNumberFormatter *numFormatter = [[NSNumberFormatter alloc] init];
            [numFormatter setNumberStyle:NSNumberFormatterDecimalStyle];
            [numFormatter setNegativeFormat:@"(#,##)"];
            
            self.volumeLabel.text =[numFormatter stringFromNumber: [NSNumber numberWithInt: summary.volume/1000]];

            self.realizedPnLLabel.text = [numFormatter stringFromNumber: [NSNumber numberWithInt: summary.realizedProfitAndLoss]];
            
            
            self.unrealizedPnLLabel.text = [numFormatter stringFromNumber: [NSNumber numberWithInt: summary.unrealizedProfitAndLoss]];
            
            self.netRiskPercentageLabel.text = [[NSString stringWithFormat:@"%.0f", summary.netRiskPercentage] stringByAppendingString: @"%"];
            
            
            [riskArc update:summary.netRiskPercentage];
            [self.positionsTableView reloadData];
            
        }
        else{
            NSLog(@"Snapshot request failed ...");
        }
        
        
    }];
}

@end
