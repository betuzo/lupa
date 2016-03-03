define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/ingreso/IngresoModel',
    'collections/PersonaCollection',
    'collections/ingreso/TipoVisibilidadCollection',
    'text!templates/private/ingreso/tplIngresoNew.html'
], function($, Backbone, BaseView, IngresoModel, PersonaCollection, TipoVisibilidadCollection, tplIngresoNew){

    var IngresoNewView = BaseView.extend({
        el: '#modal-donacion',
        template: _.template(tplIngresoNew),

        events: {

        },

        initialize: function() {
            this.model = new IngresoModel();
            this.render();

            this.visibilidades = new TipoVisibilidadCollection();
            this.listenTo(this.visibilidades, 'sync', this.syncVisibilidades);
            this.listenTo(this.visibilidades, 'add', this.agregarVisibilidad);

            this.visibilidades.fetch();

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
            $('#select-donador').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombre') + ' ' + modelo.get('paterno')
            }));
        },

        syncDonadores: function(){
        },

        agregarVisibilidad: function(modelo){
            $('#select-visibilidad').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncVisibilidades: function(){
        }
    });

    return IngresoNewView;

});