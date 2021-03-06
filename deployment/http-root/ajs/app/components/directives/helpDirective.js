(function (angular) {
    'use strict';

    //// JavaScript Code ////
    function help($log, HelpConstants) {
        return {
            restrict: 'E',
            transclude: false,
            replace: true,
            scope : {
                helpId : '@',
                helpTxt: '='
            },
            template: '<span>&nbsp;<i class="fa fa-question-circle codeine_help"></i></span>',
            link: function ($scope, element) {
                var text = '';
                if ($scope.helpTxt) {
                    text = $scope.helpTxt;
                } else {
                    text = HelpConstants[$scope.helpId] ? HelpConstants[$scope.helpId] : "No help yet for id '" + $scope.helpId +
                        "' <a href='mailto:ohad.shai@intel.com?Subject=Please%20provide%20help%20for%20" + $scope.helpId +
                        "' target='_top'>Suggest a message</a>";
                }
                element.popover(
                    {
                        content : '<span class="codeine_help_content">' + text + '</span>',
                        html : true,
                        placement : "bottom",
                        trigger : "hover",
                        delay : {
                            hide : 500
                        },
                        container :'body'
                    }
                );
                element.on('hide.bs.popover', function() {
                    $log.debug('hide.bs.popover');
                });
            }
        };
    }

    //// Angular Code ////
    angular.module('codeine').directive('help', help);

})(angular);