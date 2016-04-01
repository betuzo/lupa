define([
    'jquery',
    'backbone',
    'datetimepicker',
    'core/BaseView',
    'backboneValidation',
    'jquerySerializeObject',
    'views/private/util/ModalGenericView',
    'models/evento/EventoModel',
    'text!templates/private/evento/tplEventoNew.html'
], function($, Backbone, datetimepicker, BaseView, backboneValidation,
            jquerySerializeObject, ModalGenericView, EventoModel, tplEventoNew){

    var EventoNewView = BaseView.extend({
        el: '#modal-evento',
        template: _.template(tplEventoNew),

        events: {
            'click #btn-aceptar'        : 'saveEvento',
            'click .close-lupa'         : 'clickClose'
        },

        initialize: function(opts) {
            this.tipo = opts.tipo;
            if (this.tipo == 'new') {
                this.model = new EventoModel();
            } else {
                this.model = opts.modelo;
            }
            this.render();

            this.listenTo(this.model, "sync", this.saveEventoSuccess);
            this.listenTo(this.model, "error", this.saveEventoError);

            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#evento-dialog').modal({backdrop: "static", keyboard: false});
            this.$el.find('#dp-fecha').datetimepicker({
                format: "mm/dd/yyyy",
                pickTime: false,
                autoclose: true,
                startView: 'month',
                minView: 'month'
            });
            return this;
        },

        saveEvento: function(){
            var data = this.$el.find("#form-evento").serializeObject();
            this.model.set(data);

            if(this.model.isValid(true)){
                this.model.save();
            }
        },

        saveEventoSuccess: function(model, response, options){
            if (this.tipo === 'new') {
                app.eventBus.trigger("addEvento", model);
            } else {
                app.eventBus.trigger("editEvento" + model.get('id'), model);
                app.eventBus.trigger("updateEvento", model);
            }
            $('.close-lupa').click();
        },

        saveEventoError: function(model, response, options){
            new ModalGenericView({
                message: 'Se presento un error al registrar el evento'
            });
        },

        clickClose: function() {
            this.stopListening(this.model);
            this.undelegateEvents();
        }
    });

    return EventoNewView;

});