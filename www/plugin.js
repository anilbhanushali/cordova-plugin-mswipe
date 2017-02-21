
var exec = require('cordova/exec');

var PLUGIN_NAME = 'MSwipePlugin';

var exec = require('cordova/exec');

exports.cardPayment = function(amount,orderid,username,password,mobilenumber,appuser,productionCheck, success, error) {
	var arg = {
		"amount":amount,
		"orderid":orderid,
		"username":username,
		"password":password,
		"mobilenumber":mobilenumber,
		"appuser":appuser,
		"productionCheck":productionCheck
	}
    exec(success, error, PLUGIN_NAME, "cardPayment", [arg]);
};