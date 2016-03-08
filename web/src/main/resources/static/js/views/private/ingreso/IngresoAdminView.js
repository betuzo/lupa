define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/ingreso/IngresoTotalModel',
    'collections/ingreso/IngresoCollection',
    'views/private/ingreso/IngresoRowView',
    'views/private/ingreso/IngresoNewView',
    'text!templates/private/ingreso/tplIngresoAdmin.html'
], function($, Backbone, BaseView, IngresoTotalModel,
            IngresoCollection, IngresoRowView,
            IngresoNewView, tplIngresoAdmin){

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
        },

        render: function() {
            return this;
        },

        agregarIngreso: function(modelo){
            var vista = new IngresoRowView(modelo);
            $("#lupa-donaciones").find('tbody:last').append(vista.render().$el);
        },

        syncIngresos: function(){
        },

        syncIngresoTotal: function(){
            this.$el.html(this.template(this.model.toJSON()));
            this.ingresos.fetch();
        },

        ingresoNuevo: function(){
            new IngresoNewView({tipo: 'new', callbackNewIngreso: this.agregarIngreso});
        }
    });

    return IngresoAdminView;

});