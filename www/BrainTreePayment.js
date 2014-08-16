cordova.define("com.pdg.plugins.braintree.BrainTreePayment", function(require, exports, module) {/*
* PhoneGap plugin for BrainTree payments
* BrainTreePayment.js
*
*
* by Saul Zukauskas @sauliuz 
* PopularOwl Labs // www.popularowl.com
* @18.05.2013
*/


// constructor
//
function BrainTreePayment () {
	console.log('Construct');

}

// init with environment variables
//
BrainTreePayment.prototype.init = function(env,sandbox_mid, sandbox_encription_key, production_mid, production_encription_key, onSuccess, onFailure)
{

	environment = env || "sandbox";
	sandbox_merchant_id = sandbox_mid || "sh8t5t8sj9nj8t4h";
	production_merchant_id = production_mid || "";
	s_encription_key = sandbox_encription_key || "MIIBCgKCAQEAutFzJhxL/ZV5mYgE4+aBD+6btIDpYlaiM7EPqrqeJ/uZ8nxvvlAXs0CDJbOKNypnnu/AAsuVndfBjfCj27UWXsi7X8fFeTZuYL2NcNuRS7OYDuv6dmNWStWtqNmJF3bLC7XOkV3A/QiIAbzmpY4d6Fb+qcZDXJyuymYYYhJy2HH4Mt8dDpWSo5Fzyk70hvHQoutSMBrQNnVuErBEJccMQpVEM54/+V2eT8OUYAfx5v+tzm3/1kHleVhYzEdVKYIKzNUcOjmCLeZgsXTTvf5f9Vd8rGpahC7WTgQIZ31ykIVuc2Aqh4u47wKjlym51tvLrEJuD3Yv/RTacNtISGSiSwIDAQAB";
	p_encription_key = production_encription_key || "";

	cordova.exec(function(){console.log('Init success ');},null,"BraintreePlugin","initWithSettings", [environment, sandbox_merchant_id, s_encription_key, production_merchant_id, p_encription_key]);

    // Log
    //
    console.log('BrainTreePayment.init done');

};

    BrainTreePayment.prototype.initWithSettings = function(successCallback, failureCallback)
    {
        console.log('BrainTreePayment.getCardInfoz start');
        cordova.exec(successCallback, failureCallback, "BraintreePlugin", "initWithSettings", [])
        console.log('BrainTreePayment.getCardInfoz done');
    };

// Get card info asks Braintree library to return encrypted card info or token id card is saved
//
BrainTreePayment.prototype.getCardInfo = function(successCallback, failureCallback)
{
    console.log('BrainTreePayment.getCardInfoz start');
    cordova.exec(successCallback, failureCallback, "BraintreePlugin", "getCreditCardInfo", [])
    console.log('BrainTreePayment.getCardInfoz done');
};


// Dismisses Braintree dialog window if developer no more needs it
//
BrainTreePayment.prototype.dismiss = function(successCallback, failureCallback)
{
	cordova.exec(successCallback, failureCallback, "BraintreePlugin", "dismiss", [])
    console.log('Dismiss!!');
};

// Constructor makes all methods in this file accesible
// to JavaScript in PhoneGap application
//
cordova.addConstructor(function() {
  if(!window.plugins) {
    window.plugins = {};
  }
  if(!window.plugins.btreeplugin) {
    window.plugins.btreeplugin = new BrainTreePayment();
  }
});});
