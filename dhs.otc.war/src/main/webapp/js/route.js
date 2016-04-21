 
function route($routeProvider) {
    $routeProvider.
    when('/landingPage', {
        templateUrl: 'views/landingPage.html'
      }).
      when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl as vm'
      }).
      when('/licenserenewal', {
        templateUrl: 'views/licenserenewal.html',
        controller: 'LicenseRenewalCtrl as vm'
      }).
      when('/feesummary', {
          templateUrl: 'views/feesummary.html'
        ,controller: 'CheckoutCtrl as vm'
        }).
      otherwise({
        redirectTo: '/landingPage'
      });
  }