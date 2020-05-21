"use strict";
exports.__esModule = true;
var express = require("express");
var routerFactory_1 = require("./routerFactory");
var app = express();
var port = process.env.NODEJS_PORT || 3000;
var root = '/';
var allowCrossDomain = function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
};
var routers = [
    {
        url: 'server',
        middleware: routerFactory_1.routerFactory()
    }
];
app.use(allowCrossDomain);
routers.forEach(function (router) { return app.use(root + router.url, router.middleware); });
app.listen(port, function () { return console.log("Mock server is listening on port " + port); });
