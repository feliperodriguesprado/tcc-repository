/* 
 * Smom - Software Module Management.
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

window.angular.module('financialReleasesModule').factory('resourceCreate', function ($resource) {
    return $resource('resources/rest/create');
});

window.angular.module('financialReleasesModule').factory('resourceUpdate', function ($resource) {
    return $resource('resources/rest/update');
});

window.angular.module('financialReleasesModule').factory('resourceListById', function ($resource) {
    return $resource('resources/rest/list/:releaseId');
});

window.angular.module('financialReleasesModule').factory('resourceListByCreateDate', function ($resource) {
    return $resource('resources/rest/list');
});

window.angular.module('financialReleasesModule').factory('resourceCustomerByName', function ($resource) {
    return $resource('resources/rest/customer');
});