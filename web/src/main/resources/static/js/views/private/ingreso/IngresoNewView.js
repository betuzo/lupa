define([
    'jquery',
    'backbone',
    'core/BaseView',
    'backboneValidation',
    'jquerySerializeObject',
    'views/private/util/ModalGenericView',
    'models/ingreso/IngresoModel',
    'collections/PersonaCollection',
    'collections/ingreso/TipoVisibilidadCollection',
    'text!templates/private/ingreso/tplIngresoNew.html'
], function($, Backbone, BaseView, backboneValidation, jquerySerializeObject,
            ModalGenericView, IngresoModel, PersonaCollection,
            TipoVisibilidadCollection, tplIngresoNew){

    var IngresoNewView = BaseView.extend({
        el: '#modal-donacion',
        template: _.template(tplIngresoNew),

        events: {
            'click #btn-aceptar'        : 'saveIngreso',
            'click .close-lupa'         : 'clickClose'
        },

        initialize: function(opts) {
            if (opts.tipo == 'new') {
                this.model = new IngresoModel();
            } else {
                this.model = opts.modelo;
                $('#select-donador').prop('disabled', true);
            }
            this.callbackNewIngreso = opts.callbackNewIngreso;
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

            this.listenTo(this.model, "sync", this.saveIngresoSuccess);
            this.listenTo(this.model, "error", this.saveIngresoError);

            Backbone.Validation.bind(this);
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
            if (this.model.get('donadorId') !== '') {
                $('#select-donador').val(this.model.get('donadorId'));
            }
        },

        agregarVisibilidad: function(modelo){
            $('#select-visibilidad').append($('<option>', {
                value: modelo.get('clave'),
                text : modelo.get('descripcion')
            }));
        },

        syncVisibilidades: function(){
            $('#select-visibilidad').val(this.model.get('visibilidad'));
        },

        saveIngreso: function(){
            var data = this.$el.find("#form-ingreso").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveIngresoSuccess: function(model, response, options){
            this.callbackNewIngreso(model);
            $('.close-lupa').click();
        },

        saveIngresoError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el usuario'
            });
        },

        clickClose: function() {
            this.undelegateEvents();
        }
    });

    return IngresoNewView;

});