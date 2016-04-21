function LoginCtrl($log,$rootScope,$location,authenticationService){

 var vm=this;
	
 vm.login=function(user){
     
  authenticationService.authenticate(user)
  .success(function(data, status) {
            if(data){ 
            	
            	
            	$rootScope.userId=user.login;	
            	
            	$location.path('/licenserenewal');
            
            }
          else{
               $log .log("unhautorized");
          }
        });     
 }
 
 vm.logout=function(){
     
  authenticationService.logout()
  .success(function(data, status) {
         $log .log("you are logged out"); 
         $rootScope.userId="";
        });     
 }
	
};



function CheckoutCtrl($scope,renewalService,$location){
	
	var vm=this;
	
	renewalService.getFeeSummary().success(function (response){
		
		vm.feeSummaryData=response.data;
		if (!$scope.$$phase) $scope.$apply();
		
	});
	

	
	
	vm.proceed=function(){
		renewalService.checkoutProceed().success(function(response){
			
			if(response.redirectTo){window.location.assign(response.redirectTo);}
			
			console.log(angular.toJson(response));
			
		});
		
	}
	
}




function LicenseRenewalCtrl(renewalService,$scope,$rootScope,$location){
	var vm=this;
	
	vm.renewalInputs={empowerPack:false,lateFee:false,userId:$rootScope.userId};
	
	var childCareOptions=["CCGH","CC10","CC59","CC60"];
	
	var dlspOption="DLSP";
	
	var nbsOption="NBS";
	
	vm.childCare=false;
	vm.dlsp=false;
	vm.other=false;
	
	vm.userId=$rootScope.userId;
	
	renewalService.getLicenseTypes()
	.success(function(data){vm.licenseTypes=data;if (!$scope.$$phase) $scope.$apply();});
	
	
	
	vm.submit=function(){
	
		vm.renewalInputs.userId= vm.userId;
		renewalService.checkForm(vm.renewalInputs).success(function(response){
			
			if(response.data=="valid"){
				
				$location.path("feesummary");
				
			}else{
				
				vm.messages=response.messages;
			}
			
			
			
			
		});
		
	}
	
	
	vm.licenseTypeSelectionChanged=function(licenseType){
		
		
		vm.renewalInputs.licenseType=angular.fromJson(licenseType);
		
		if(-1 !== childCareOptions.indexOf(vm.renewalInputs.licenseType.shortDesc)){
			vm.childCare=true;
			vm.dlsp=false;
			vm.other=false;
			vm.renewalInputs.numberOfBeds=null;
			
			vm.amount=false;
			vm.copyFee=true;
			vm.renewalInputs.amountPenality=null;
			vm.invoiceNumber=false;
			vm.renewalInputs.invoiceNumber=null;
			
		}else{
			vm.childCare=false;
			vm.renewalInputs.lateFee=false;
			vm.renewalInputs.empowerPack=false;
			
			
			if(dlspOption==vm.renewalInputs.licenseType.shortDesc||nbsOption==vm.renewalInputs.licenseType.shortDesc){
				
				vm.dlsp=true;
				vm.other=false;
				vm.renewalInputs.numberOfBeds=null;
				vm.amount=true;
				vm.copyFee=false;
				vm.invoice=true;
				vm.renewalInputs.copyFees=null;
				
				
			}else{
				vm.other=true;
				vm.dlsp=false;
				vm.copyFee=true;
				vm.amount=false;
				vm.invoice=false;
				vm.renewalInputs.amountPenality=null;
				vm.renewalInputs.invoiceNumber=null;
			}
		}
		
		
	};
	
	
};



