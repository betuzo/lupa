define([
    'jquery',
    'backbone',
    'core/BaseView',
    'backboneValidation',
    'jquerySerializeObject',
    'views/private/util/ModalGenericView',
    'models/egreso/EgresoModel',
    'models/util/PhotoModel',
    'collections/PersonaCollection',
    'collections/evento/EventoCollection',
    'views/private/util/UploadFileView',
    'text!templates/private/egreso/tplEgresoNew.html'
], function($, Backbone, BaseView, backboneValidation, jquerySerializeObject,
            ModalGenericView, EgresoModel, PhotoModel, PersonaCollection,
            EventoCollection, UploadFileView, tplEgresoNew){

    var EgresoNewView = BaseView.extend({
        el: '#modal-gasto',
        template: _.template(tplEgresoNew),

        events: {
            'click #btn-aceptar'        : 'saveEgreso',
            'click .close-lupa'         : 'clickClose'
        },

        initialize: function(opts) {
            this.tipo = opts.tipo;
            if (this.tipo == 'new') {
                this.model = new EgresoModel();
            } else {
                this.model = opts.modelo;
            }
            this.render();

            this.eventos = new EventoCollection();
            this.listenTo(this.eventos, 'sync', this.syncEventos);
            this.listenTo(this.eventos, 'add', this.agregarEvento);

            this.eventos.fetch();

            this.listenTo(this.model, "sync", this.saveEgresoSuccess);
            this.listenTo(this.model, "error", this.saveEgresoError);

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#egreso-dialog').modal({backdrop: "static", keyboard: false});
            if (this.tipo !== 'new') {
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

        agregarEvento: function(modelo){
            $('#select-evento').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('eventoNombre')
            }));
        },

        syncEventos: function(){
            if (this.model.get('eventoId') !== '') {
                $('#select-evento').val(this.model.get('eventoId'));
            }
        },

        saveEgreso: function(){
            var data = this.$el.find("#form-egreso").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveEgresoSuccess: function(model, response, options){
            if (this.tipo === 'new') {
                app.eventBus.trigger("addEgreso", model);
            } else {
                app.eventBus.trigger("editEgreso" + model.get('id'), model);
                app.eventBus.trigger("updateEgreso", model);
            }
            $('.close-lupa').click();
        },

        saveEgresoError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el egreso'
            });
        },

        clickClose: function() {
            this.stopListening(this.model);
            this.undelegateEvents();
        }
    });

    return EgresoNewView;

});