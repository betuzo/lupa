define([
    'jquery',
    'underscore',
    'core/BaseView',
    'text!templates/private/egreso/tplEgresoDetail.html'
], function($, _, BaseView, tplEgresoDetail){

    var EgresoDetailView = BaseView.extend({
        el: '#modal-gasto',
        template: _.template(tplEgresoDetail),

        events: {
            'click .close-lupa'         : 'clickClose'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            this.$('#egreso-dialog').modal({backdrop: "static", keyboard: false});
            return this;
        },

        clickClose: function() {
            this.undelegateEvents();
        }
    });

    return EgresoDetailView;

});