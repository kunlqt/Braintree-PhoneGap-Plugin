
#import <Cordova/CDV.h>
#import <Security/Security.h>
#import "FDKeychain.h"
#import <Braintree/BTPaymentViewController.h>
// This is js-native wrapper for Braintree SDK for iOS

@interface iOSBraintreePhoneGapPlugin : CDVPlugin<BTPaymentViewControllerDelegate>

// initlise braintree with api keys
- (void)initWithSettings:(CDVInvokedUrlCommand *)command;
// present view to get credit card details
- (void)getCreditCardInfo:(CDVInvokedUrlCommand *)command;
// dismiss view
- (void)dismiss:(CDVInvokedUrlCommand *)command;

@end
