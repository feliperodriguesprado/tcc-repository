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
var app = window.angular.module('app', ['ngRoute', 'ngResource']);

app.config(['$routeProvider', function ($routeProvider) {

        'use strict';

        $routeProvider.
                when('/', {
                    controller: 'controller',
                    templateUrl: '_dashboard.html'
                }).
                when('/dashboard', {
                    controller: 'controller',
                    templateUrl: '_dashboard.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]);

app.controller('controller', function ($scope) {

    console.log("Start controller app module");

});