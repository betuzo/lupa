define([
    'jquery',
    'backbone',
    'highcharts',
    'highcharts3d',
    'exporting',
    'core/BaseView',
    'text!templates/private/resumen/tplResumenAdmin.html'
], function($, Backbone, highcharts, highcharts3d, exporting, BaseView, tplResumenAdmin){

    var ResumenAdminView = BaseView.extend({
        template: _.template(tplResumenAdmin),

        events: {

        },

        initialize: function() {

        },

        render: function() {
            this.$el.html(this.template());
            this.setUp();
            this.pushData();
            return this;
        },

        setUp: function() {
            this.$el.find('#grafica-ingreso').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45
                    }
                },
                title: {
                    text: 'Ingresos San Antonio 2013'
                },
                subtitle: {
                    text: 'Ingresos por eventos'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        innerSize: 100,
                        depth: 45
                    }
                }
            });
            this.$el.find('#grafica-egreso').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45
                    }
                },
                title: {
                    text: 'Egresos San Antonio 2013'
                },
                subtitle: {
                    text: 'Egresos por eventos'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        innerSize: 100,
                        depth: 45
                    }
                }
            });
        },

        pushData: function(){
            var ingresosChart = this.$el.find('#grafica-ingreso').highcharts();
            ingresosChart.addSeries({
                name: 'Total',
                data: [
                    ['Bananas', 8],
                    ['Kiwi', 3],
                    ['Mixed nuts', 1],
                    ['Oranges', 6],
                    ['Apples', 8],
                    ['Pears', 4],
                    ['Clementines', 4],
                    ['Reddish (bag)', 1],
                    ['Grapes (bunch)', 1]
                ]
            });

            var egresosChart = this.$el.find('#grafica-egreso').highcharts();
            egresosChart.addSeries({
                name: 'Total',
                data: [
                    ['Bananas', 8],
                    ['Kiwi', 3],
                    ['Mixed nuts', 1],
                    ['Oranges', 6],
                    ['Apples', 8],
                    ['Pears', 4],
                    ['Clementines', 4],
                    ['Reddish (bag)', 1],
                    ['Grapes (bunch)', 1]
                ]
            });
        }
    });

    return ResumenAdminView;

});