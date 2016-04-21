function authenticationService($http) {

	var service = this;

	function authenticate(user) {

		var req = {
			method : 'POST',
			url : 'rest/authenticate',
			headers : {
				'Content-Type' : 'application/x-authc-username-password+json'
			},
			data : {
				userId : user.login,
				password : user.password,
				agencyId : 1
			}
		};

		// the agency id is dummy here !!!

		return $http(req);

	}

	function logout() {

		var req = {
			method : 'POST',
			url : 'rest/logout',
			headers : {
				'Content-Type' : 'application/json'
			},
		// data: {userId: user.login,password:user.password,agencyId:1}

		};

		// the agency id is dummy here !!!

		return $http(req);

	}

	service.logout = logout;
	service.authenticate = authenticate;

	return service;

};



function renewalService($http,$log) {
	
	var service = this;

	

service.getFeeSummary=function(){
		
		var req = {
				method : 'GET',
				url : 'rest/RenewalAction/feeSummary',
				headers : {
					'Content-Type' : 'application/json'
				}
			};

			return $http(req);
		
	}
	
	
	service.checkForm=function(renewalInputs){
		
		var req = {
				method : 'POST',
				url : 'rest/RenewalAction/SubmitForm',
				headers : {
					'Content-Type' : 'application/json'
				},
			 data: renewalInputs

			};

			// the agency id is dummy here !!!

			return $http(req);
		
	}
	
	service.checkoutProceed=function(){
		
		var req = {
				method : 'POST',
				url : 'rest/RenewalAction/beginCheckout'
			};
		
		 return $http(req);
		
	}
	
	service.getLicenseTypes = function() {

		var retrieveLicenseTypeListRequest = {
			method : 'GET',
			url : 'rest/RenewalAction/LicenseTypeList'
		};

		 return $http(retrieveLicenseTypeListRequest);
		

	};


	return service;

};
