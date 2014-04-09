(function () {
    'use strict';
    angular.module('poolingpeopleApp')

        .service('LoadStatusService', function () {

            var NOT_REQUESTED = 0,
                RESOLVING = 1,
                CHILD_RESOLVING = 2,
                SUCCEED = 3,
                FAILED = 4,
                COMPLETED = 5,
                CACHED = 6,
                loadStatus = {
                    status: NOT_REQUESTED,
                    date: new Date(),
                    subresources: {},
                    subresourcesLoading: 0,
                    path: ""
                },
                getResource = function (resource) {
                    var resourceLevels = resource.split("."),
                        depth = 0,
                        maxDepth = resourceLevels.length,
                        resourceTarget = loadStatus;
                    if (resource === "") return loadStatus; // change this, maybe a do - while loop is not useful;

                    do {
                        resourceTarget = resourceTarget.subresources[resourceLevels[depth]];
                        depth = depth + 1;
                    } while (maxDepth > depth && resourceTarget !== undefined);
                    return resourceTarget;
                },
                getParent = function (resource) {
                    var resourceLevels = resource.split(".");
                    return getResource(resourceLevels.slice(0, resourceLevels.length - 1).join("."));
                },
                getChild = function (resource, child) {
                    return getResource(resource + "." + child);
                },
                getChildren = function (resource, recursive) {
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
                },
                getAncestors = function (resource) {
                    var ancestors = [],
                        resourceTarget = getParent(resource);
                    do {
                        ancestors.push(resourceTarget);
                        resourceTarget = getParent(resourceTarget.path);
                    } while (resourceTarget.path !== "");
                    return ancestors;
                },
                isRequested = function (resource) {
                    return getResource(resource) !== undefined;
                },
                isLoading = function (resource, inheritance) {
                    var resourceTarget = getResource(resource);
                    inheritance = (inheritance === true) ? true : false;
                    return (resourceTarget !== undefined) && (resourceTarget.status === RESOLVING ||
                        (resourceTarget.status === CHILD_RESOLVING && inheritance));
                },
                isCompleted = function (resource) {
                    var resourceTarget = getResource(resource);
                    return (resourceTarget !== undefined) && (resourceTarget.status === COMPLETED || resourceTarget === CACHED);
                },
                isCached = function (resource) {
                    var resourceTarget = getResource(resource);
                    return (resourceTarget !== undefined) && (resourceTarget.status === CACHED);
                },
                getStatus = function (resource) {
                    var resourceTarget = getResource(resource);
                    return (resourceTarget !== undefined) ? resourceTarget.status : NOT_REQUESTED;
                },
                setStatus = function (resource, newStatus, date) {
                    var resourceLevels = resource.split("."),
                        depth = 0,
                        maxDepth = resourceLevels.length,
                        resourceTarget = loadStatus,
                        path = [];
                    while (maxDepth > depth) {
                        path.push(resourceLevels[depth]);
                        resourceTarget.subresources[resourceLevels[depth]] = resourceTarget.subresources[resourceLevels[depth]] ||
                            {status: NOT_REQUESTED,
                                date: new Date(),
                                subresources: {},
                                subresourcesLoading: 0,
                                path: path.join(".")
                            };
                        resourceTarget = resourceTarget.subresources[resourceLevels[depth++]];
                    }

                    resourceTarget.status = newStatus;
                    resourceTarget.date = date ? new Date() : resourceTarget.date;

                    var ancestors = getAncestors(resourceTarget.path);
                    if (isLoading(resourceTarget.path)) {
                        for (var i = 0, ii = ancestors.length; i < ii; i++) {
                            ancestors[i].subresourcesLoading++;
                            ancestors[i].status = CHILD_RESOLVING;
                        }
                    } else if (isCompleted(resourceTarget.path)) {
                        for (var i = 0, ii = ancestors.length; i < ii; i++) {
                            ancestors[i].subresourcesLoading--;
                            if (ancestors[i].subresourcesLoading <= 0) {
                                ancestors[i].subresourcesLoading = 0;
                                ancestors[i].status = COMPLETED;
                            }
                        }
                    }

                };

            return {
                NOT_REQUESTED: NOT_REQUESTED,
                RESOLVING: RESOLVING,
                CHILD_RESOLVING: CHILD_RESOLVING,
                SUCCEED: SUCCEED,
                FAILED: FAILED,
                COMPLETED: COMPLETED,
                CACHED: CACHED,
                isRequested: isRequested,
                isLoading: isLoading,
                isCompleted: isCompleted,
                isCached: isCached,
                getStatus: getStatus,
                setStatus: setStatus
            };
        });
}());