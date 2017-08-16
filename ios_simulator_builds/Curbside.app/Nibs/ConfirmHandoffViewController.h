//
//  ConfirmHandoffViewController.h
//  nCurbside
//
//  Created by Radwar on 8/5/14.
//  Copyright (c) 2014 curbside. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CSPickup;
@class CSHandoffRecipient;

@interface ConfirmHandoffViewController : UITableViewController

@property (nonatomic, strong)CSPickup *pickup;
/// Done button in the navigation bar that will modally dismiss the view controller
@property (nonatomic, assign) BOOL showDoneButton;

@end
