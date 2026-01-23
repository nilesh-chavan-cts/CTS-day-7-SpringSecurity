angular.module("EmailModule").controller("EmailController", EmailController);

function EmailController($scope, $state, EmployeeFactory) {

    $scope.email = "";
    $scope.message = "";
    $scope.loading = false;

    $scope.sendOtp = function () {
        if (!$scope.email) {
            $scope.message = "Please enter email address";
            return;
        }

        $scope.loading = true;
        $scope.message = "";

		EmployeeFactory.sendOtp($scope.email)
		    .then(function (res) {

		        if (res.data === "OTP Sended...!") {
		            $scope.message = "OTP sent successfully to your email";
		            localStorage.setItem("email", $scope.email);
		            $state.go("otp");
		        } else {
		            $scope.message = "Unexpected response from server";
		        }

		    })
		    .catch(function (err) {
		        console.error(err);
		        $scope.message = "Failed to send OTP";
		    })
		    .finally(function () {
		        $scope.loading = false;
		    });
	}
}
