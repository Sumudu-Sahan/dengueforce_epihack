var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
  $scope.subscribes = [
    {name: 'Holy Family Convent'},
    {name: 'Shangrila Constructions'},
    {name: 'Gangarama Temple'},
    {name: 'World Trade Center'}
  ];
  $scope.subscriptions = [
    {name: 'Royal College'},
    {name: 'Cinnamon Lakeside'},
    {name: 'Bambalapitiya'},
    {name: 'Kollupitiya'},
    {name: 'Kalutara'},
  ];

  $scope.notifications = [
    {text: 'Dengue kill 10 people within 1 day', href: './message.html'},
    {text: 'Dengue fever prevention', href: './message.html'},
    {text: 'High Risk Area', href: './message.html'},
  ];

  $scope.currentItem = null;

  $scope.addItem = function() {
    $scope.subscribes.push($scope.currentItem);
    $scope.subscriptions.shift($scope.currentItem);
    $("#myModal").modal("hide");
  }

  $scope.setCurrentItem = function(item) {
    $scope.currentItem = item;
    $("#myModal").modal()
  }

  $scope.removeMe = function(item) {
    $scope.subscriptions.unshift(item);

    var index = $scope.subscribes.indexOf(item);
    $scope.subscribes.splice(index, 1);
  }

});
