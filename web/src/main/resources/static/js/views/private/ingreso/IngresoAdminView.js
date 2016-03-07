define([
    'jquery',
    'backbone',
    'core/BaseView',
    'collections/ingreso/IngresoCollection',
    'views/private/ingreso/IngresoRowView',
    'views/private/ingreso/IngresoNewView',
    'text!templates/private/ingreso/tplIngresoAdmin.html'
], function($, Backbone, BaseView, IngresoCollection, IngresoRowView, IngresoNewView, tplIngresoAdmin){

    var IngresoAdminView = BaseView.extend({
        template: _.template(tplIngresoAdmin),

        events: {
            'click #ingreso-nuevo'        : 'ingresoNuevo'
        },

        initialize: function() {
            this.ingresos = new IngresoCollection();
            this.listenTo(this.ingresos, 'sync', this.syncIngresos);
            this.listenTo(this.ingresos, 'add', this.agregarIngreso);

            this.ingresos.fetch();
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

        ingresoNuevo: function(){
            new IngresoNewView({tipo: 'new', callbackNewIngreso: this.agregarIngreso});
        }
    });

    return IngresoAdminView;

});