require.config({
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
        backbonePaginator: {
            deps: [
                'backbone'
            ]
        },
        backboneValidation: {
            deps: [
                'backbone'
            ]
        },
        backgrid: {
            deps: [
                'backbone'
            ]
        },
        backgridPaginator: {
            deps: [
                'backgrid',
                'backbonePaginator'
            ]
        },
        backgridSellectAll: {
            deps: [
                'backgrid'
            ]
        },
        backgridFilter: {
            deps: [
                'backgrid'
            ]
        },
        backgridSum: {
            deps: [
                'backgrid'
            ]
        },
        bootstrap: {
            deps: [
                'jquery'
            ]
        },
        selecter: {
            deps: [
                'jquery'
            ]
        },
        jquerycookie:{
            deps: [
                'jquery'
            ]        },
        jquerySerializeObject: {
            deps: [
                'jquery'
            ]
        },
        datetimepicker: {
            deps: [
                'jquery'
            ]
        },
        bloodhound: {
            deps: [
                'jquery'
            ]
        },
        typeahead: {
            deps: [
                'jquery'
            ]
        },
        highcharts: {
            deps: [
                'jquery'
            ]
        },
        highcharts3d: {
            deps: [
                'highcharts'
            ]
        },
        exporting: {
            deps: [
                'highcharts'
            ]
        }
    },
    paths: {
        backbone: 'vendor/backbone/backbone-min',
        backbonePaginator: 'vendor/backbone/paginator/backbone.paginator.min',
        jquery: 'vendor/jquery/jquery',
        jquerycookie: 'vendor/jquery/cookie/jquery.cookie',
        jquerySerializeObject: 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        backgrid: 'vendor/backbone/backgrid/backgrid',
        backgridPaginator: 'vendor/backbone/backgrid/backgrid-paginator.min',
        backgridSellectAll: 'vendor/backbone/backgrid/backgrid-select-all.min',
        backgridFilter: 'vendor/backbone/backgrid/backgrid-filter.min',
        backgridSum: 'vendor/backbone/backgrid/backgrid-sum',
        backboneValidation: 'vendor/backbone/backbone-validation/backbone-validation',
        text : 'vendor/requirejs-text/text',
        underscore: 'vendor/underscore/underscore-min',
        bootstrap: 'vendor/bootstrap/bootstrap',
        selecter: 'vendor/bootstrap/select/bootstrap-select.min',
        datetimepicker: 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        dateformat: 'vendor/date-format/date.format',
        bloodhound: 'vendor/typeahead/bloodhound',
        typeahead: 'vendor/typeahead/typeahead.jquery',
        highcharts: 'vendor/highcharts/highcharts',
        highcharts3d: 'vendor/highcharts/highcharts-3d',
        exporting: 'vendor/highcharts/exporting'
    }
});

require([
    'app'
], function (App) {
    var app = new App();
    app.start();
});