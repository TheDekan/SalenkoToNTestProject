 var app = angular.module('clients', ['ngResource', 'ngGrid', 'ui.bootstrap']);

app.controller('clientsListController', function ($scope, $rootScope, clientService) {
    
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.clients = {currentPage: 1};

    $scope.gridOptions = {
        data: 'clients.clientList',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id', width: 30 },
            { field: 'name', displayName: 'Name' },
            { field: 'phone', displayName: 'Phone' },   
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }          
        ],

        multiSelect: false,
        selectedItems: [],
        
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('clientSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

        $scope.refreshGrid = function () {
        var listClientsArgs = {
            page: $scope.clients.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        clientService.get(listClientsArgs, function (data) {
            $scope.clients = data;
        })
    };

        $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteClient', row.entity.id);
    };

        $scope.$watch('sortInfo', function () {
        $scope.clients = {currentPage: 1};
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

app.controller('clientsFormController', function ($scope, $rootScope, clientService) {
    
    	$scope.clearForm = function () {
        $scope.client = null;
        $scope.clientForm.$setPristine();
        $rootScope.$broadcast('clear');
    };

        $scope.updateClient = function () {
        clientService.save($scope.client).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('clientSaved');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    };

    	$scope.$on('clientSelected', function (event, id) {
        $scope.client = clientService.get({id: id});
    });

        $scope.$on('deleteClient', function (event, id) {
        clientService.delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('clientDeleted');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    });
});

app.controller('alertMessagesController', function ($scope) {
    	$scope.$on('clientSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

        $scope.$on('clientDeleted', function () {
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

app.factory('clientService', function ($resource) {
    return $resource('resources/clients/:id');
});

