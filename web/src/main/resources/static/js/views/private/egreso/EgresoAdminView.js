define([
    'jquery',
    'backbone',
    'backgrid',
    'backgridPaginator',
    'backgridSellectAll',
    'backgridFilter',
    'core/BaseView',
    'models/egreso/EgresoTotalModel',
    'collections/egreso/EgresoPageableCollection',
    'views/private/egreso/EgresoActionCell',
    'views/private/egreso/EgresoNewView',
    'views/private/egreso/EgresoTotalView',
    'text!templates/private/egreso/tplEgresoAdmin.html'
], function($, Backbone, backgrid, backgridPaginator, backgridSellectAll, backgridFilter,
            BaseView, EgresoTotalModel, EgresoPageableCollection, EgresoActionCell,
            EgresoNewView, EgresoTotalView, tplEgresoAdmin){

    var EgresoAdminView = BaseView.extend({
        template: _.template(tplEgresoAdmin),

        events: {
            'click #egreso-nuevo'        : 'egresoNuevo'
        },

        initialize: function() {
            this.model = new EgresoTotalModel();
            this.model.set({id: 1});
            this.listenTo(this.model, 'sync', this.syncEgresoTotal);

            this.model.fetch();

            this.listenTo(app.eventBus, 'addEgreso', this.agregarEgresoNew);
            this.listenTo(app.eventBus, 'deleteEgreso', this.quitarEgreso);
            this.listenTo(app.eventBus, 'updateEgreso', this.modificarEgreso);
        },

        render: function() {
            this.$el.html(this.template());
            this.setUp();
            return this;
        },

        syncEgresoTotal: function(){
            new EgresoTotalView(this.model);
        },

        agregarEgresoNew: function(modelo){
            this.model.fetch();
            this.egresoPageableCollection.add(modelo);
        },

        quitarEgreso: function(modelo){
            this.model.fetch();
        },

        modificarEgreso: function(modelo){
            this.model.fetch();
        },

        egresoNuevo: function(){
            new EgresoNewView({tipo: 'new'});
        },

        setUp: function(){
            var MyRow = Backgrid.Row.extend({
                render: function () {
                    MyRow.__super__.render.apply(this, arguments);
                        if (this.model.get("enabled")==='VALIDA') {
                            this.$el.addClass("status-validado");
                        } else {
                            this.$el.addClass("status-no-validado");
                        }
                    return this;
                }
            });

            var columns = [{
                name: "id", // The key of the model attribute
                label: "ID", // The name to display in the header
                editable: false, // By default every cell in a column is editable, but *ID* shouldn't be
                // Defines a cell type, and ID is displayed as an integer without the ',' separating 1000s.
                cell: Backgrid.IntegerCell.extend({
                    orderSeparator: ''
                })
                }, {
                    name: "recaudadorNombre",
                    label: "Recaudador",
                    editable: false,
                    // The cell type can be a reference of a Backgrid.Cell subclass, any Backgrid.Cell subclass instances like *id* above, or a string
                    cell: "string" // This is converted to "StringCell" and a corresponding class in the Backgrid package namespace is looked up
                }, {
                    name: "eventoNombre",
                    label: "Evento",
                    editable: false,
                    cell: "string"
                }, {
                    name: "monto",
                    label: "Monto",
                    editable: false,
                    cell: "number" // A cell type for floating point value, defaults to have a precision 2 decimal numbers
                }, {
                    name: "fechaRegistro",
                    label: "Fecha Registro",
                    editable: false,
                    cell: "date"
                }, {
                    name: "enabledDes",
                    label: "Status",
                    editable: false,
                    cell: "string"
                }, {
                    name: "",
                    label: "",
                    cell: EgresoActionCell
            }];

            this.egresoPageableCollection = new EgresoPageableCollection();

            var pageableGrid = new Backgrid.Grid({
                columns: columns,
                collection: this.egresoPageableCollection,
                row: MyRow
            });

            // Render the grid
            var $example2 = this.$el.find("#example-2-result");
            $example2.append(pageableGrid.render().el)

            // Initialize the paginator
            var paginator = new Backgrid.Extension.Paginator({
                collection: this.egresoPageableCollection
            });

            // Render the paginator
            $example2.after(paginator.render().el);

            // Initialize a client-side filter to filter on the client
            // mode pageable collection's cache.
            var filter = new Backgrid.Extension.ClientSideFilter({
              collection: this.egresoPageableCollection,
              fields: ['recaudadorNombre', 'eventoNombre']
            });

            // Render the filter
            $example2.before(filter.render().el);

            // Add some space to the filter and move it to the right
            $(filter.el).css({float: "right", margin: "20px"});

            // Fetch some data
            this.egresoPageableCollection.fetch({reset: true});
        }
    });

    return EgresoAdminView;

});