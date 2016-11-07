var app = angular.module('visits', ['ngResource', 'ngGrid', 'ui.bootstrap']);

app.controller('visitsListController', function ($scope, $rootScope, visitService) {
    
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.visits = {currentPage: 1};

    $scope.gridOptions = {
        data: 'visits.visitList',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
         //   { field: 'id', displayName: 'Id' },
            { field: '1', displayName: 'Visit Time', width: 160 },
            { field: '4', displayName: 'Place Address' },
            { field: '2', displayName: 'Phone' },
            { field: '3', displayName: 'Master Name' }
         //   ,{ field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }          
        ],

        multiSelect: false,
        selectedItems: [],
        
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('visitSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

        $scope.refreshGrid = function () {
        var listVisitsArgs = {
            page: $scope.visits.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        visitService.get(listVisitsArgs, function (data) {
            $scope.visits = data;
        })
    };

        $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteVisit', row.entity.id);
    };

        $scope.$watch('sortInfo', function () {
        $scope.visits = {currentPage: 1};
        $scope.refreshGrid();
    }, true);

        $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

        $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });

        $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
});

app.controller('visitsFormController', function ($scope, $rootScope, visitService) {
    
	$scope.placeList = [];
	
	visitService.get(function (data) {
   	 	$scope.placeList = data.placeList;
    })
	
    $scope.clearForm = function () {
        $scope.visit = null;
        $scope.visitForm.$setPristine();
        $rootScope.$broadcast('clear');
    };

    $scope.updateVisit = function () {
        visitService.save($scope.visit).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('visitSaved');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    };

    $scope.$on('visitSelected', function (event, id) {
        $scope.visit = visitService.get({id: id});
    });

    $scope.$on('deleteVisit', function (event, id) {
        visitService.delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('visitDeleted');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    });
});

app.controller('alertMessagesController', function ($scope) {
    	$scope.$on('visitSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

        $scope.$on('visitDeleted', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record deleted successfully!' }
        ];
    });

        $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'There was a problem in the server! or you trying to use already used visit time.' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

app.factory('visitService', function ($resource) {
    return $resource('resources/visits/:id');
});
