define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/ingreso/IngresoModel',
    'collections/PersonaCollection',
    'text!templates/private/ingreso/tplIngresoNew.html'
], function($, Backbone, BaseView, IngresoModel, PersonaCollection, tplIngresoNew){

    var IngresoNewView = BaseView.extend({
        el: '#modal-donacion',
        template: _.template(tplIngresoNew),

        events: {

        },

        initialize: function() {
            this.model = new IngresoModel();
            this.render();

            this.donadores = new PersonaCollection();
            this.listenTo(this.donadores, 'sync', this.syncDonadores);
            this.listenTo(this.donadores, 'add', this.agregarDonador);

            this.donadores.setTipo('role');
            this.donadores.setRole('DONADOR');

            this.donadores.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#ingreso-dialog').modal({backdrop: "static", keyboard: false});
            return this;
        },

        agregarDonador: function(modelo){

        },

        syncDonadores: function(){
        }
    });

    return IngresoNewView;

});