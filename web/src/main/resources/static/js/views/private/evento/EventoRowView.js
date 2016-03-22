define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/evento/EventoNewView',
    'views/private/evento/EventoDetailView',
    'text!templates/private/evento/tplEventoRow.html'
], function($, _, dateformat, BaseView, ModalGenericView, EventoNewView, EventoDetailView, tplEventoRow){

    var EventoRowView = BaseView.extend({
        template: _.template(tplEventoRow),
        tagName: 'tr',

        events: {
            'click #eliminar-evento'        : 'eliminarEvento',
            'click #editar-evento'          : 'editarEvento',
            'click #detalle-evento'         : 'detalleEvento'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});
        },

        render: function() {
            this.model.set({classAction: this.classAction(this.model.get('enabled'))});
            this.model.set({classDetail: this.classDetail(this.model.get('enabled'))});
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.addClass(this.classDonacion(this.model.get('enabled')));
            return this;
        },

        classDonacion: function(enabled) {
            if (enabled==='VALIDA')
                return 'success';
            else
                return 'danger';
        },

        classAction: function(enabled) {
            if (enabled==='REGISTRADA')
                return '';
            else
                return 'element-hidden';
        },

        classDetail: function(enabled) {
            if (enabled==='VALIDA')
                return '';
            else
                return 'element-hidden';
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
        },

        detalleEvento: function() {
            new EventoDetailView(this.model);
        }
    });

    return EventoRowView;

});