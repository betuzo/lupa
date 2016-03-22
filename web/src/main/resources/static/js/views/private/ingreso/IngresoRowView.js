define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/ingreso/IngresoNewView',
    'views/private/ingreso/IngresoDetailView',
    'text!templates/private/ingreso/tplIngresoRow.html'
], function($, _, dateformat, BaseView, ModalGenericView, IngresoNewView, IngresoDetailView, tplIngresoRow){

    var IngresoRowView = BaseView.extend({
        template: _.template(tplIngresoRow),
        tagName: 'tr',

        events: {
            'click #eliminar-ingreso'        : 'eliminarIngreso',
            'click #editar-ingreso'          : 'editarIngreso',
            'click #detalle-ingreso'         : 'detalleIngreso'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});

            this.listenTo(app.eventBus, 'editIngreso'+this.model.get('id'), this.editarSaveIngreso);
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

        eliminarIngreso: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    app.eventBus.trigger("deleteIngreso", model);
                    that.destroyView();
                    new ModalGenericView({message: 'Ingreso eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        editarIngreso: function() {
            new IngresoNewView({modelo: this.model, tipo: 'edit'});
        },

        editarSaveIngreso: function(model) {
            this.model = model;
            this.render();
        },

        detalleIngreso: function() {
            new IngresoDetailView(this.model);
        }
    });

    return IngresoRowView;

});