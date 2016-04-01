define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/evento/EventoNewView',
    'text!templates/private/evento/tplEventoRow.html'
], function($, _, dateformat, BaseView, ModalGenericView, EventoNewView, tplEventoRow){

    var EventoRowView = BaseView.extend({
        template: _.template(tplEventoRow),
        tagName: 'tr',

        events: {
            'click #eliminar-evento'        : 'eliminarEvento',
            'click #editar-evento'          : 'editarEvento'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaEventoDes: (new Date(this.model.get('fechaEvento'))).format("mm/dd/yyyy")});

            this.listenTo(app.eventBus, 'editEvento'+this.model.get('id'), this.editarSaveEvento);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        eliminarEvento: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    new ModalGenericView({message: 'Evento eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        editarEvento: function() {
            new EventoNewView({modelo: this.model, tipo: 'edit', callbackNewEvento: this.editarSaveEvento});
        },

        editarSaveEvento: function(model) {
            this.model = model;
            this.render();
        }
    });

    return EventoRowView;

});