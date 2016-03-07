define([
    'jquery',
    'underscore',
    'core/BaseView',
    'text!templates/private/ingreso/tplIngresoDetail.html'
], function($, _, BaseView, tplIngresoDetail){

    var IngresoDetailView = BaseView.extend({
        el: '#modal-donacion',
        template: _.template(tplIngresoDetail),

        events: {
            'click .close-lupa'         : 'clickClose'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#ingreso-dialog').modal({backdrop: "static", keyboard: false});
            return this;
        },

        clickClose: function() {
            this.undelegateEvents();
        }
    });

    return IngresoDetailView;

});