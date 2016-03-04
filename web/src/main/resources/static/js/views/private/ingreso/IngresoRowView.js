define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/ingreso/IngresoNewView',
    'text!templates/private/ingreso/tplIngresoRow.html'
], function($, _, dateformat, BaseView, ModalGenericView, IngresoNewView, tplIngresoRow){

    var IngresoRowView = BaseView.extend({
        template: _.template(tplIngresoRow),
        tagName: 'tr',

        events: {
            'click #eliminar-ingreso'        : 'eliminarIngreso',
            'click #editar-ingreso'          : 'editarIngreso'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});
        },

        render: function() {
            this.model.set({classAction: this.classAction(this.model.get('enabled'))});
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

        eliminarIngreso: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    new ModalGenericView({message: 'Ingreso eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        editarIngreso: function() {
            new IngresoNewView({modelo: this.model, tipo: 'edit', callbackNewIngreso: this.editarSaveIngreso});
        },

        editarSaveIngreso: function(model) {
            this.model = model;
            this.render();
        }
    });

    return IngresoRowView;

});