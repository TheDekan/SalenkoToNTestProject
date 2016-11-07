 var app = angular.module('places', ['ngResource', 'ngGrid', 'ui.bootstrap']);

app.controller('placesListController', function ($scope, $rootScope, placeService) {
    
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.places = {currentPage: 1};

    $scope.gridOptions = {
        data: 'places.placeList',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
           // { field: 'id', displayName: 'Id' },
            { field: '1', displayName: 'Address' },
            { field: '2', displayName: 'Master Name' }   
        //    ,{ field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }          
        ],

        multiSelect: false,
        selectedItems: [],
        
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('placeSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

    $scope.refreshGrid = function () {
        var listPlacesArgs = {
            page: $scope.places.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        placeService.get(listPlacesArgs, function (data) {
            $scope.places = data;
        })
    };

        $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deletePlace', row.entity.id);
    };

        $scope.$watch('sortInfo', function () {
        $scope.places = {currentPage: 1};
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

app.controller('placesFormController', function ($scope, $rootScope, placeService) {
	
	$scope.clientList = [];
	
	placeService.get(function (data) {
   	 	$scope.clientList = data.clientList;
    })
	
    $scope.clearForm = function () {
        $scope.place = null;
        $scope.placeForm.$setPristine();
        $rootScope.$broadcast('clear');
    };
    
    $scope.updatePlace = function () {
        placeService.save($scope.place).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('placeSaved');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    };

	$scope.$on('placeSelected', function (event, id) {
        $scope.place = placeService.get({id: id});
    });

        $scope.$on('deletePlace', function (event, id) {
        placeService.delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('placeDeleted');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    });
});

app.controller('alertMessagesController', function ($scope) {
    	$scope.$on('placeSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

        $scope.$on('placeDeleted', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record deleted successfully!' }
        ];
    });

        $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'There was a problem in the server!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

app.factory('placeService', function ($resource) {
    return $resource('resources/places/:id');
});

