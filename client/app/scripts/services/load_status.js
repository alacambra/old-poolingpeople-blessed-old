(function () {
    'use strict';

    angular.module('poolingpeopleApp')

        .service('LoadStatusService', function(DataProvider) {

            var NOT_REQUESTED = 0,
                RESOLVING = 1,
                CHILD_RESOLVING = 2,
                SUCCEED = 3,
                FAILED = 4,
                COMPLETED = 5;

            var loadStatus = {
                status: NOT_REQUESTED,
                subresources: {},
                path: ""
            };

            var getResource = function(resource) {
                var resourceLevels = resource.split("."),
                    depth = 0,
                    maxDepth = resourceLevels.length,
                    resourceTarget = loadStatus;

                do {
                    resourceTarget = resourceTarget.subresources[resourceLevels[depth++]];
                } while (maxDepth > depth && resourceTarget !== undefined);

                return resourceTarget;
            }

            var getParent = function(resource) {
                var resourceLevels = resource.split(".");
                return getResource(resourceLevels.slice(0, resourceLevels.length - 1).join("."));
            }

            var getChild = function(resource, child) {
                return getResource(resource + "." + child);
            }

            var getChildren = function(resource, recursive) {
                var children = [],
                    resourceTarget = getResource(resource);
                for (var key in resourceTarget.subresources) {
                    children.push(resourceTarget.subresources[key]);
                    if (recursive) {
                        var grandChildren = resourceTarget.subresources[key];
                        if (grandChildren.length > 0)
                            children.push(getChildren(grandChildren.path, true));
                    }
                }
                return children;
            }

            return {

                NOT_REQUESTED : NOT_REQUESTED,
                RESOLVING : RESOLVING,
                CHILD_RESOLVING : CHILD_RESOLVING,
                SUCCEED : SUCCEED,
                FAILED : FAILED,
                COMPLETED : COMPLETED,

                isRequested: function(resource) {
                    return getResource(resource) !== undefined;
                },
                isLoading: function(resource, inheritance) {
                    var resourceTarget = getResource(resource);
                    inheritance = (inheritance === true) ? true : false;
                    return (resourceTarget !== undefined) && (resourceTarget.status === RESOLVING || (resourceTarget.status === CHILD_RESOLVING && inheritance));
                },
                isCompleted: function(resource) {
                    var resourceTarget = getResource(resource);
                    return (resourceTarget !== undefined) && (resourceTarget.status === COMPLETED);
                },
                getStatus: function(resource) {
                    var resourceTarget = getResource(resource);
                    return (resourceTarget !== undefined) ? resourceTarget.status : NOT_REQUESTED;
                },
                setStatus: function(resource, newStatus) {
                    var resourceLevels = resource.split("."),
                        depth = 0,
                        maxDepth = resourceLevels.length,
                        resourceTarget = loadStatus,
                        path = [];

                    while (maxDepth > depth) {
                        path.push(resourceLevels[depth]);
                        resourceTarget.subresources[resourceLevels[depth]] = resourceTarget.subresources[resourceLevels[depth]] || 
                                                                             {status: NOT_REQUESTED, subresources: {}, path: path.join(".")}
                        resourceTarget = resourceTarget.subresources[resourceLevels[depth++]];
                    }

                    resourceTarget.status = newStatus;
                }
            }
    });

}());