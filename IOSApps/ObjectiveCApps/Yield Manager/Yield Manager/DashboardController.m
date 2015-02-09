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

@interface DashboardController ()
@property (weak, nonatomic) IBOutlet UITableView *positionsTableView;

-(void)getRWSnapShot;

@property (nonatomic, strong) NSArray *snapshots;

@end

@implementation DashboardController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.positionsTableView.dataSource = self;
    
    self.positionsTableView.rowHeight = 100;
    
    [self.positionsTableView registerNib:[UINib nibWithNibName:@"PositionsViewCell" bundle:nil] forCellReuseIdentifier:@"PositionCell"];
  
    
    [self getRWSnapShot];
    
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
            
            NSLog(@"Summary : %f", summary.yieldPerMillion);
//            loadSnapshots(snapshots);
            
            [self.positionsTableView reloadData];
            
        }
        else{
            NSLog(@"Snapshot request failed ...");
        }
        
        
    }];
}

@end
