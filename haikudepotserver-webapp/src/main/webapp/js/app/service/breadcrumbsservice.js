/*
 * Copyright 2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

/**
 * <p>This service helps in the creation of breadcrumb items.</p>
 */

angular.module('haikudepotserver').factory('breadcrumbs',
    [
        function() {

            var BreadcrumbsService = {

                createViewUser : function(user) {
                    return {
                        title : user.nickname,
                        path : '/viewuser/' + user.nickname
                    };
                },

                createChangePassword : function(user) {
                    return {
                        title : 'Change Password',
                        path : '/changepassword/' + user.nickname
                    };
                }
            };

            return BreadcrumbsService;

        }
    ]
);