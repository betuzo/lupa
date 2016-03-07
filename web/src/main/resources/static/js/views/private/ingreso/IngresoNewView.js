define([
    'jquery',
    'backbone',
    'core/BaseView',
    'backboneValidation',
    'jquerySerializeObject',
    'views/private/util/ModalGenericView',
    'models/ingreso/IngresoModel',
    'models/util/PhotoModel',
    'collections/PersonaCollection',
    'collections/ingreso/TipoVisibilidadCollection',
    'views/private/util/UploadFileView',
    'text!templates/private/ingreso/tplIngresoNew.html'
], function($, Backbone, BaseView, backboneValidation, jquerySerializeObject,
            ModalGenericView, IngresoModel, PhotoModel, PersonaCollection,
            TipoVisibilidadCollection, UploadFileView, tplIngresoNew){

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
            }
            this.callbackNewIngreso = opts.callbackNewIngreso;
            this.render(opts.tipo);

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

        render: function(tipo) {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#ingreso-dialog').modal({backdrop: "static", keyboard: false});
            if (tipo === 'new') {
                $('#select-donador').prop('disabled', false);
            } else {
                $('#select-donador').prop('disabled', true);
                this.setUpEdit();
            }
            return this;
        },

        setUpEdit: function() {
            var that = this;
            var photo = new PhotoModel();
            photo.set({pathLogo: this.model.get('rutaFichaPago')});
            photo.set({hasLogo: this.model.get('hasFichaPago')});
            photo.set({idLogo: this.model.get('id')});
            photo.set({nameLogo: this.model.get('fichaPago')});
            photo.set({type: 'INGRESO'});
            var uploadFile = new UploadFileView({
                modelo: photo,
                urlUpload: 'file/upload/foto',
                urlDelete: 'file/delete/foto',
                callbackUpload:function (data) {
                    that.model.set({hasFichaPago: true});
                    that.model.set({rutaFichaPago: data.pathfilename});
                    that.model.set({fichaPago: data.filename});
                },
                callbackDelete:function (data) {
                    that.model.set({hasFichaPago: false});
                    that.model.set({rutaFichaPago: data.defaultname});
                    that.model.set({fichaPago: ''});
                }
            });
            $('#upload-file').html(uploadFile.render().$el);
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