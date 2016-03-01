define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'text!templates/private/ingreso/tplIngresoRow.html'
], function($, _, dateformat, BaseView, tplIngresoRow){

    var IngresoRowView = BaseView.extend({
        template: _.template(tplIngresoRow),
        tagName: 'tr',

        events: {

        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$el.addClass(this.classDonacion(this.model.get('enabled')));
            return this;
        },

        classDonacion: function(enabled) {
            if (enabled==='VALIDA')
                return 'success';
            else
                return 'danger';
        }
    });

    return IngresoRowView;

});