//
//  ConfirmHandoffViewController.m
//  nCurbside
//
//  Created by Radwar on 8/5/14.
//  Copyright (c) 2014 curbside. All rights reserved.
//

#import "ConfirmHandoffViewController.h"
#import "AppSession.h"
#import "CurbsideExtras.h"
#import "CSUserSession.h"
#import "StoreTableViewCell.h"
#import "CSPickup.h"
#import "CSStoreCache.h"
#import "AppExtras.h"
#import "CSHandOffEntity.h"
#import "CSHandOff.h"
#import "CSHandOffAPIRequester.h"
#import "CSPickupsAPIRequester.h"
#import <Analytics/SEGAnalytics.h>
#import "CSSiteArrivalTracker.h"
#import "CSDebugLogger.h"
#import "CSPickup+AD.h"
#import "MyOrderViewController.h"

static NSString * ConfirmHandoffHeaderIdentifier = @"ConfirmHandoffHeader";
static NSString * ConfirmHandoffPickupPreviewIdentifier = @"PickupPreviewTableViewCell";

typedef NS_ENUM(NSInteger, TableViewSection) {
    TableViewSectionHeader = 0,
    TableViewSectionPickupPreview,
    TableViewSectionCount
};

@interface ConfirmHandoffHeader : UITableViewCell

@property (strong, nonatomic) IBOutlet UILabel *messageLabel;
@property (strong, nonatomic) IBOutlet UIButton *rejectHandoffButton;
@property (strong, nonatomic) IBOutlet UIButton *acceptHandoffButton;

@end

@implementation ConfirmHandoffHeader

- (void)awakeFromNib
{
    [super awakeFromNib];
    self.backgroundColor = [UIColor csBackgroundCellColor];
    [_rejectHandoffButton csConfigureStandardButton];
    [_acceptHandoffButton csConfigureStandardButton];
}

@end

#pragma mark -

@interface ConfirmHandoffViewController ()
{
    BOOL _requestInFlight;
}

@property (nonatomic, strong) MyOrderViewController *myOrderViewController;

@end

@implementation ConfirmHandoffViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.title = @"Pickup Request";
    
    UITableView *tableView = self.tableView;
    tableView.backgroundColor = [UIColor csBackgroundCellColor];
    [tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:ConfirmHandoffPickupPreviewIdentifier];
    tableView.tableFooterView = [[UIView alloc] initWithFrame:CGRectZero];
    
    if (_showDoneButton) {
        self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemDone target:self action:@selector(dismiss)];
    }
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [[SEGAnalytics sharedAnalytics] screen:@"Confirm Handoff" properties:nil];

    // Fetch the store.
    [[CSStoreCache sharedCache] fetchStoreWithStoreID:_pickup.storeID];
}

#pragma mark - UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return TableViewSectionCount;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSUInteger section = indexPath.section;
    UITableViewCell *cell;

    switch (section) {
        case TableViewSectionHeader:
        {
            cell = [tableView dequeueReusableCellWithIdentifier:ConfirmHandoffHeaderIdentifier forIndexPath:indexPath];
            ConfirmHandoffHeader *headerCell = (ConfirmHandoffHeader *)cell;
            if (headerCell.rejectHandoffButton.allTargets.count == 0) {
                [headerCell.rejectHandoffButton addTarget:self action:@selector(rejectHandoff:) forControlEvents:UIControlEventTouchUpInside ];
                [headerCell.acceptHandoffButton addTarget:self action:@selector(acceptHandoff:) forControlEvents:UIControlEventTouchUpInside];
            }
            
            CSHandOff *handoff = _pickup.handoff;
            CSHandOffEntity *requestingEntity = handoff.requestingEntity;
            NSString *requestingName = requestingEntity.firstName;
            if (requestingName.length == 0) {
                requestingName = requestingEntity.displayName;
            }
            NSString *receivingName = handoff.receivingEntity.displayName;
            
            NSString *message = [NSString stringWithFormat:@"Hi %@!\n\n%@ would like you to pick up a Curbside order.\n\nThe order is paid for. Just pick it up from the store before %@.\n\nDo you accept this pickup request?", receivingName, requestingName, _pickup.pickupByDateString];
            
            headerCell.messageLabel.text = message;
            break;
        }
        case TableViewSectionPickupPreview:
        {
            cell = [tableView dequeueReusableCellWithIdentifier:ConfirmHandoffPickupPreviewIdentifier forIndexPath:indexPath];
            
            if (_myOrderViewController == nil) {
                _myOrderViewController = [[UIStoryboard storyboardWithName:@"MyOrderViewController" bundle:nil] instantiateInitialViewController];
                
                [self addChildViewController:_myOrderViewController];
                [cell.contentView addSubview:_myOrderViewController.view];
                [_myOrderViewController didMoveToParentViewController:self];
                
                [_myOrderViewController configureForPickup:_pickup];
            }
            _myOrderViewController.view.frame = cell.contentView.bounds;
            
            break;
        }
    }
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == TableViewSectionPickupPreview) {
        UIViewController *viewController = ViewControllerForPickupBasedOnPickupStatus(_pickup);
        if ([viewController respondsToSelector:@selector(setPickup:)]) {
            [(id)viewController setPickup:_pickup];
        }
        [self.navigationController pushViewController:viewController animated:YES];
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    switch (indexPath.section) {
        case TableViewSectionHeader:
            return UITableViewAutomaticDimension;
        case TableViewSectionPickupPreview:
            return 180;
        default:
            return 0;
    }
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return UITableViewAutomaticDimension;
}

-(void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if ([tableView respondsToSelector:@selector(setSeparatorInset:)]) {
        [tableView setSeparatorInset:UIEdgeInsetsZero];
    }
    
    if ([tableView respondsToSelector:@selector(setLayoutMargins:)]) {
        [tableView setLayoutMargins:UIEdgeInsetsZero];
    }
    
    if ([cell respondsToSelector:@selector(setLayoutMargins:)]) {
        [cell setLayoutMargins:UIEdgeInsetsZero];
    }
}

#pragma mark - Helpers

- (void)_processOrderWithAction:(CSHandOffActionType)action;
{
    _requestInFlight = YES;
    CSHandOffAPIRequester *requester = [[CSHandOffAPIRequester alloc] initWithUser:[AppSession currentSession].user finishedHandler:nil];
    requester.pickup = _pickup;
    requester.handOffActionType = action;
    requester.handOffID = _pickup.handoff.handoffID;
    CSStatus status;
    NSError *error;
    [requester startSynchronousReturnStatusCode:&status error:&error];
    
    [[AppSession currentSession] updateCartsAndFetchPickups:NO showShoppingCartAfterFetch:NO completionHandler:^(BOOL success) {
        _requestInFlight = NO;
        if (success) {
            if (action == CSHandOffActionTypeAccept) {
                NSString *storeID = _pickup.storeID;
                NSString *newPickupID = _pickup.adTrackToken;

                // Add the tracked store.
                CSUserSession *session = [CSUserSession currentSession];
                [session startTripToSiteWithIdentifier:storeID trackToken:newPickupID];
            }
        }
    }];
}


- (IBAction)rejectHandoff:(id)sender {
    if (_requestInFlight)
        return;
    
    [[SEGAnalytics sharedAnalytics] track:@"Reject HandOff" properties:@{@"order_id" : _pickup.pickupID}];
    [self _processOrderWithAction:CSHandOffActionTypeReject];
    [self dismiss];
}

- (IBAction)acceptHandoff:(id)sender {
    if (_requestInFlight)
        return;
    
    [[SEGAnalytics sharedAnalytics] track:@"Accept HandOff" properties:@{@"order_id" : _pickup.pickupID}];
    [self _processOrderWithAction:CSHandOffActionTypeAccept];
    [[[UIAlertView alloc] initWithTitle:@"Thanks For Accepting" message:@"Your friend’s order information will be listed in accounts with the rest of your orders." delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
}

- (void)dismiss
{
    [self.navigationController dismissViewControllerAnimated:YES completion:nil];
}

#pragma mark - UIAlertViewDelegate

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    [self dismiss];
}

@end
