app.controller("AuthController", ["$scope", "$location", "AuthService", function($scope, $location, AuthService) {
    $scope.users = AuthService.getUsers();

    $scope.register = function() {
        let result = AuthService.register($scope.newUser);
        $scope.message = result;
        if (result === "Registration successful!") {
            $scope.newUser = {};
            $location.path("/login"); // Redirect to login after registration
        }
    };

    $scope.login = function() {
        let result = AuthService.login($scope.loginData);
        if (result.success) {
            $scope.message = result.message;
            $location.path("/dashboard"); // Redirect to dashboard on success
        } else {
            $scope.message = result.message;
        }
    };
}]);

app.controller("DashboardController", ["$scope", function($scope) {
    $scope.message = "Welcome to your dashboard!";
}]);