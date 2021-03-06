/* 
 * Copyright 2015 Smom - Software Module Management.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function customerListCtrl($scope, $window, notification, messages, encryption, serverResponse, log, uiGridConstants, resourceListCreatedRanking, resourceListAll) {

    var rowTemplate = "<div ng-dblclick=\"grid.appScope.dblClickRow(row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>",
            filterList = 'show';

    /*
     * Scope passed to grid for control events rows grid
     */
    $scope.scopeCustomerList = {
        dblClickRow: function (row) {
            $window.location.href = "#/customer-register/" + row.entity.id;
        }
    };

    /**
     * Configuration grid
     */
    $scope.grid = {
        appScopeProvider: $scope.scopeCustomerList,
        columnDefs: getConfigColumns(),
        paginationPageSizes: [],
        paginationPageSize: 10,
        enableRowSelection: true,
        multiSelect: false,
        enableSelectAll: false,
        enableRowHeaderSelection: false,
        enableFiltering: false,
        enableColumnMenu: false,
        //rowHeight: 60,
        enableHorizontalScrollbar: 1,
        enableVerticalScrollbar: 1,
        rowTemplate: rowTemplate
    };

    /**
     * Register action in grid.
     * 
     * @param gridApi
     */
    $scope.grid.onRegisterApi = function (gridApi) {

        //set gridApi on scope
        $scope.gridApi = gridApi;

//        gridApi.selection.on.rowSelectionChanged($scope, function (row) {
//            notification.info(JSON.stringify(row.entity));
//        });
//
//        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rowList) {
//            rowList.forEach(function (row) {
//                notification.info(JSON.stringify(row.entity));
//            });
//        });
    };

    /**
     * Function that get array with set columns of grid
     * @returns {Array}
     */
    function getConfigColumns() {
        return [
            {name: 'id', displayName: 'Código', width: "13%", enableColumnMenu: false},
            {name: 'name', displayName: 'Nome', width: "41%", enableColumnMenu: false},
            {name: 'cpfCnpj', displayName: 'CPF/CNPJ', width: "23%", enableColumnMenu: false},
            {name: 'dateCreate', displayName: 'Data de criação', width: "23%", enableColumnMenu: false}
        ];
    }

    $scope.filter = function () {
        if (filterList === 'hide') {
            $('#filterList').collapse(filterList);
            filterList = 'show';
        }

        $scope.grid.enableFiltering = !$scope.grid.enableFiltering;
        $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
    };

    $scope.showFilterList = function () {
        $('#filterList').collapse(filterList);

        if (filterList === 'show') {
            filterList = 'hide';
        } else {
            filterList = 'show';
        }
    };

    $scope.addCustomer = function () {
        notification.info("Não implementado");
    };

    $scope.search = function () {
        notification.info("Não implementado");
    };

    $scope.listAll = function () {

        resourceListAll.get({}, function (data) {
            try {
                if (data.responseResource.code === serverResponse.INFO_GET_CUSTOMER_LIST) {
                    $scope.grid.data = data.customerList;

                    var sizeItemList = data.customerList.length,
                            pageSize = 0;

                    for (var i = 0; i < sizeItemList / 10; i = i + 1) {
                        $scope.grid.paginationPageSizes.push(pageSize + 10);
                        pageSize = pageSize + 10;
                    }

                } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                    $('#modalUnavailableModule').modal('show');
                } else {
                    notification.showMessage(data.responseResource);
                }
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        }, function () {
            try {
                log.error(messages.ERROR_REQUEST_SERVER);
                notification.error(messages.ERROR_REQUEST_SERVER);
            } catch (e) {
                log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
                notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
            }
        });

    };

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    resourceListCreatedRanking.get({position: "10"}, function (data) {
        try {
            if (data.responseResource.code === serverResponse.INFO_GET_CUSTOMER_LIST) {
                $scope.grid.data = data.customerList;
            } else if (data.responseResource.code === serverResponse.WARN_UNAVAILABLE_MODULE) {
                $('#modalUnavailableModule').modal('show');
            } else {
                notification.showMessage(data.responseResource);
            }
        } catch (e) {
            log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
            notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
        }
    }, function () {
        try {
            log.error(messages.ERROR_REQUEST_SERVER);
            notification.error(messages.ERROR_REQUEST_SERVER);
        } catch (e) {
            log.error(messages.ERROR_PERFORM_OPERATION_SYSTEM, e);
            notification.showMessage(messages.ERROR_PERFORM_OPERATION_SYSTEM);
        }
    });

    $scope.closeModal = function (element) {
        $('#' + element).modal('hide');
    };

}