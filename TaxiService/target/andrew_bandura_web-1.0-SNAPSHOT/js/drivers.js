/**
 * Created by Andrew on 06.11.2015.
 */

angular.module('app', ['ngResource'] )
    .controller("DrvCtrl", function ($scope, $http) {


            console.log('/getHeader invoked');
            $http.get('/getHeader').
                success(function(data, status, headers, config) {
                    $scope.header = data;
                }).
                error(function(data, status, headers, config) {
                    $scope.header="not authorized user";
                });

        $scope.getAll = function(){
            console.log('/getAll invoked');
            $http.get('/getAllOrders').
                success(function(data, status, headers, config) {
                    $scope.orders = data;
                }).
                error(function(data, status, headers, config) {
                    $scope.orders=null;
                    console.log("error occured while gettin' orders");
                    // log error
                });
        }
    });
