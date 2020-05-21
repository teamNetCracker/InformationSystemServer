"use strict";
exports.__esModule = true;
var express = require("express");
exports.routerFactory = function () {
    var router = express.Router();
    router.get('/tracks', function (req, res) {
        res.status(200).json([
            {
                name: 'Name 1',
                author: 'Author 1'
            },
            {
                name: 'Name 2',
                author: 'Author 2'
            }
        ]);
    });
    router.get('/genres', function (req, res) {
        res.status(200).json([
            {
                name: 'Genre 1'
            },
            {
                name: 'Genre 2'
            }
        ]);
    });
    return router;
};
