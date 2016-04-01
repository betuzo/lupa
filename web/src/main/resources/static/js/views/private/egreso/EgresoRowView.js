define([
    'jquery',
    'underscore',
    'dateformat',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/egreso/EgresoNewView',
    'views/private/egreso/EgresoDetailView',
    'text!templates/private/egreso/tplEgresoRow.html'
], function($, _, dateformat, BaseView, ModalGenericView, EgresoNewView, EgresoDetailView, tplEgresoRow){

    var EgresoRowView = BaseView.extend({
        template: _.template(tplEgresoRow),
        tagName: 'tr',

        events: {
            'click #eliminar-egreso'        : 'eliminarEgreso',
            'click #editar-egreso'          : 'editarEgreso',
            'click #detalle-egreso'         : 'detalleEgreso'
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});

            this.listenTo(app.eventBus, 'editEgreso'+this.model.get('id'), this.editarSaveEgreso);
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

        eliminarEgreso: function() {
            that = this;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    app.eventBus.trigger("deleteEgreso", model);
                    that.destroyView();
                    new ModalGenericView({message: 'Egreso eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        editarEgreso: function() {
            new EgresoNewView({modelo: this.model, tipo: 'edit'});
        },

        editarSaveEgreso: function(model) {
            this.model = model;
            this.render();
        },

        detalleEgreso: function() {
            new EgresoDetailView(this.model);
        }
    });

    return EgresoRowView;

});