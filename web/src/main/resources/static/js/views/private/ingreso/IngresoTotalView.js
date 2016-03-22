define([
    'jquery',
    'underscore',
    'core/BaseView',
    'text!templates/private/ingreso/tplIngresoTotal.html'
], function($, _, BaseView, tplIngresoTotal){

    var IngresoTotalView = BaseView.extend({
        el: '#ingreso-totales',
        template: _.template(tplIngresoTotal),

        events: {
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });

    return IngresoTotalView;

});