define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/ingreso/IngresoTotalModel',
    'collections/ingreso/IngresoCollection',
    'views/private/ingreso/IngresoRowView',
    'views/private/ingreso/IngresoNewView',
    'views/private/ingreso/IngresoTotalView',
    'text!templates/private/ingreso/tplIngresoAdmin.html'
], function($, Backbone, BaseView, IngresoTotalModel,
            IngresoCollection, IngresoRowView,
            IngresoNewView, IngresoTotalView,
            tplIngresoAdmin){

    var IngresoAdminView = BaseView.extend({
        template: _.template(tplIngresoAdmin),

        events: {
            'click #ingreso-nuevo'        : 'ingresoNuevo'
        },

        initialize: function() {
            this.model = new IngresoTotalModel();
            this.model.set({id: 1});
            this.listenTo(this.model, 'sync', this.syncIngresoTotal);

            this.ingresos = new IngresoCollection();
            this.listenTo(this.ingresos, 'sync', this.syncIngresos);
            this.listenTo(this.ingresos, 'add', this.agregarIngreso);

            this.model.fetch();
            this.ingresos.fetch();

            this.listenTo(app.eventBus, 'addIngreso', this.agregarIngresoNew);
            this.listenTo(app.eventBus, 'deleteIngreso', this.quitarIngreso);
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarIngreso: function(modelo){
            var vista = new IngresoRowView(modelo);
            $("#lupa-donaciones").find('tbody:last').append(vista.render().$el);
        },

        syncIngresos: function(){
        },

        syncIngresoTotal: function(){
            new IngresoTotalView(this.model);
        },

        agregarIngresoNew: function(modelo){
            this.model.fetch();
            this.agregarIngreso(modelo);
        },

        quitarIngreso: function(modelo){
            this.model.fetch();
        },

        ingresoNuevo: function(){
            new IngresoNewView({tipo: 'new'});
        }
    });

    return IngresoAdminView;

});