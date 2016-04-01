define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/egreso/EgresoTotalModel',
    'collections/egreso/EgresoCollection',
    'views/private/egreso/EgresoRowView',
    'views/private/egreso/EgresoNewView',
    'views/private/egreso/EgresoTotalView',
    'text!templates/private/egreso/tplEgresoAdmin.html'
], function($, Backbone, BaseView, EgresoTotalModel, EgresoCollection,
            EgresoRowView, EgresoNewView, EgresoTotalView, tplEgresoAdmin){

    var EgresoAdminView = BaseView.extend({
        template: _.template(tplEgresoAdmin),

        events: {
            'click #egreso-nuevo'        : 'egresoNuevo'
        },

        initialize: function() {
            this.model = new EgresoTotalModel();
            this.model.set({id: 1});
            this.listenTo(this.model, 'sync', this.syncEgresoTotal);

            this.egresos = new EgresoCollection();
            this.listenTo(this.egresos, 'sync', this.syncEgresos);
            this.listenTo(this.egresos, 'add', this.agregarEgreso);

            this.model.fetch();
            this.egresos.fetch();

            this.listenTo(app.eventBus, 'addEgreso', this.agregarEgresoNew);
            this.listenTo(app.eventBus, 'deleteEgreso', this.quitarEgreso);
            this.listenTo(app.eventBus, 'updateEgreso', this.modificarEgreso);
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarEgreso: function(modelo){
            var vista = new EgresoRowView(modelo);
            $("#lupa-donaciones").find('tbody:last').append(vista.render().$el);
        },

        syncEgresos: function(){
        },

        syncEgresoTotal: function(){
            new EgresoTotalView(this.model);
        },

        agregarEgresoNew: function(modelo){
            this.model.fetch();
            this.agregarEgreso(modelo);
        },

        quitarEgreso: function(modelo){
            this.model.fetch();
        },

        modificarEgreso: function(modelo){
            this.model.fetch();
        },

        egresoNuevo: function(){
            new EgresoNewView({tipo: 'new'});
        }
    });

    return EgresoAdminView;

});