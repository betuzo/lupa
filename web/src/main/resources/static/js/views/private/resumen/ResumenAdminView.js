define([
    'jquery',
    'backbone',
    'highcharts',
    'highcharts3d',
    'exporting',
    'core/BaseView',
    'models/ingreso/IngresoTotalModel',
    'models/egreso/EgresoTotalModel',
    'collections/util/GrapCollection',
    'text!templates/private/resumen/tplResumenAdmin.html'
], function($, Backbone, highcharts, highcharts3d, exporting,
            BaseView, IngresoTotalModel, EgresoTotalModel,
            GrapCollection, tplResumenAdmin){

    var ResumenAdminView = BaseView.extend({
        template: _.template(tplResumenAdmin),

        events: {

        },

        initialize: function() {
            this.modelIngresoTotal = new IngresoTotalModel();
            this.modelIngresoTotal.set({id: 1});
            this.listenTo(this.modelIngresoTotal, 'sync', this.syncIngresoTotal);

            this.modelEgresoTotal = new EgresoTotalModel();
            this.modelEgresoTotal.set({id: 1});
            this.listenTo(this.modelEgresoTotal, 'sync', this.syncEgresoTotal);

            this.listIngresosDetail = [];
            this.listEgresosDetail = [];
            this.ingresosDetail = new GrapCollection();
            this.ingresosDetail.setTipo('ingreso');
            this.egresosDetail = new GrapCollection();
            this.egresosDetail.setTipo('egreso');

            this.listenTo(this.ingresosDetail, 'sync', this.syncIngresosDetail);
            this.listenTo(this.ingresosDetail, 'add', this.agregarIngresosDetail);
            this.listenTo(this.egresosDetail, 'sync', this.syncEgresosDetail);
            this.listenTo(this.egresosDetail, 'add', this.agregarEgresosDetail);
        },

        render: function() {
            this.$el.html(this.template());
            this.setUp();
            this.ingresosDetail.fetch();
            this.egresosDetail.fetch();
            this.modelIngresoTotal.fetch();
            return this;
        },

        syncIngresoTotal: function() {
            this.$el.find('#tpi').html(this.modelIngresoTotal.get('totalPendientes'));
            this.$el.find('#tsvi').html(this.modelIngresoTotal.get('totalMontoPendiente'));
            this.$el.find('#tti').html(this.modelIngresoTotal.get('totalMonto'));
            this.modelEgresoTotal.fetch();
        },

        syncEgresoTotal: function() {
            this.$el.find('#tpe').html(this.modelEgresoTotal.get('totalPendientes'));
            this.$el.find('#tsve').html(this.modelEgresoTotal.get('totalMontoPendiente'));
            this.$el.find('#tte').html(this.modelEgresoTotal.get('totalMonto'));

            this.$el.find('#tpc').html(
                this.modelIngresoTotal.get('totalPendientes') + this.modelEgresoTotal.get('totalPendientes'));
            this.$el.find('#tsvc').html(
                this.modelIngresoTotal.get('totalMontoPendiente') - this.modelEgresoTotal.get('totalMontoPendiente'));
            this.$el.find('#ttc').html(
                this.modelIngresoTotal.get('totalMonto') - this.modelEgresoTotal.get('totalMonto'));
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
                tooltip: {
                    pointFormat: '$ {point.y:.2f} - {point.count}'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        innerSize: 100,
                        depth: 45,
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
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
                tooltip: {
                    pointFormat: '$ {point.y:.2f} - {point.count}'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        innerSize: 100,
                        depth: 45,
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                }
            });
        },

        agregarIngresosDetail: function(model){
            this.listIngresosDetail.push({name: model.get('label'), y: model.get('y'), count: model.get('count')});
        },

        syncIngresosDetail: function(){
            var ingresosChart = this.$el.find('#grafica-ingreso').highcharts();
            ingresosChart.addSeries({
                name: 'Total',
                data: this.listIngresosDetail
            });
        },

        agregarEgresosDetail: function(model){
            this.listEgresosDetail.push({name: model.get('label'), y: model.get('y'), count: model.get('count')});
        },

        syncEgresosDetail: function(){
            var egresosChart = this.$el.find('#grafica-egreso').highcharts();
            egresosChart.addSeries({
                name: 'Total',
                data: this.listEgresosDetail
            });
        }

    });

    return ResumenAdminView;

});