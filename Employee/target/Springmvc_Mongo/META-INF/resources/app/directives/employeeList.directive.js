angular.module("AdminModule")
.directive("employeeList", function() {
    return {
        restrict: "E",
        scope: {
            employees: "=",
            onView: "&",
            onEdit: "&",
            onDelete: "&",
            searchText: "="
        },
        templateUrl: "app/components/AdminComponents/partials/employee-list.html"
    };
});
