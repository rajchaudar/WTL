var app = angular.module("myApp", ["ngRoute"]);

app.config(["$routeProvider", function($routeProvider) {
    $routeProvider
        .when("/register", {
            templateUrl: "views/register.html",
            controller: "AuthController"
        })
        .when("/login", {
            templateUrl: "views/login.html",
            controller: "AuthController"
        })
        .when("/dashboard", {
            templateUrl: "views/dashboard.html",
            controller: "DashboardController"
        })
        .otherwise({
            redirectTo: "/login"
        });
}]);