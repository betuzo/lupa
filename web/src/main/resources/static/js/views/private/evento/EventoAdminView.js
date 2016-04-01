define([
    'jquery',
    'backbone',
    'core/BaseView',
    'collections/evento/EventoCollection',
    'views/private/evento/EventoRowView',
    'views/private/evento/EventoNewView',
    'text!templates/private/evento/tplEventoAdmin.html'
], function($, Backbone, BaseView, EventoCollection,
            EventoRowView, EventoNewView, tpleventoAdmin){

    var EventoAdminView = BaseView.extend({
        template: _.template(tpleventoAdmin),

        events: {
            'click #evento-nuevo'        : 'eventoNuevo'
        },

        initialize: function() {
            this.eventos = new EventoCollection();
            this.listenTo(this.eventos, 'sync', this.syncEventos);
            this.listenTo(this.eventos, 'add', this.agregarEvento);

            this.eventos.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        agregarEvento: function(modelo){
            var vista = new EventoRowView(modelo);
            $("#lupa-eventos").find('tbody:last').append(vista.render().$el);
        },

        syncEventos: function(){
        },

        eventoNuevo: function(){
            new EventoNewView({tipo: 'new', callbackNewEvento: this.agregarEvento});
        }
    });

    return EventoAdminView;

});