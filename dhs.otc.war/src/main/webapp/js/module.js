angular.module("DHS_OTC_APP",['ngResource','ngRoute'])

.factory("authenticationService",authenticationService)
.factory("renewalService",renewalService)

.config(['$routeProvider',route])
.directive('goClick',goClick)
.directive('emptyToNull',emptyToNull)  

.controller("LoginCtrl",LoginCtrl)
.controller("LicenseRenewalCtrl",LicenseRenewalCtrl)
.controller("CheckoutCtrl",CheckoutCtrl)
;