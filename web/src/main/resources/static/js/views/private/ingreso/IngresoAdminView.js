define([
    'jquery',
    'backbone',
    'backgrid',
    'backgridPaginator',
    'backgridSellectAll',
    'backgridFilter',
    'backgridSum',
    'core/BaseView',
    'models/ingreso/IngresoTotalModel',
    'collections/ingreso/IngresoPageableCollection',
    'views/private/ingreso/IngresoActionCell',
    'views/private/ingreso/IngresoNewView',
    'views/private/ingreso/IngresoTotalView',
    'text!templates/private/ingreso/tplIngresoAdmin.html'
], function($, Backbone, backgrid, backgridPaginator, backgridSellectAll, backgridFilter,
            backgridSum, BaseView, IngresoTotalModel, IngresoPageableCollection, IngresoActionCell,
            IngresoNewView, IngresoTotalView, tplIngresoAdmin){

    var IngresoAdminView = BaseView.extend({
        template: _.template(tplIngresoAdmin),

        events: {
            'click #ingreso-nuevo'        : 'ingresoNuevo'
        },

        initialize: function() {
            this.model = new IngresoTotalModel();
            this.model.set({id: 1});
            this.listenTo(this.model, 'sync', this.syncIngresoTotal);

            this.model.fetch();

            this.listenTo(app.eventBus, 'addIngreso', this.agregarIngresoNew);
            this.listenTo(app.eventBus, 'deleteIngreso', this.quitarIngreso);
            this.listenTo(app.eventBus, 'updateIngreso', this.modificarIngreso);
        },

        render: function() {
            this.$el.html(this.template());
            this.setUp();
            return this;
        },

        syncIngresoTotal: function(){
            new IngresoTotalView(this.model);
        },

        agregarIngresoNew: function(modelo){
            this.model.fetch();
            this.ingresoPageableCollection.add(modelo);
        },

        quitarIngreso: function(modelo){
            this.model.fetch();
        },

        modificarIngreso: function(modelo){
            this.model.fetch();
        },

        ingresoNuevo: function(){
            new IngresoNewView({tipo: 'new'});
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
                    name: "donadorNombre",
                    label: "Donador",
                    editable: false,
                    // The cell type can be a reference of a Backgrid.Cell subclass, any Backgrid.Cell subclass instances like *id* above, or a string
                    cell: "string" // This is converted to "StringCell" and a corresponding class in the Backgrid package namespace is looked up
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
                    name: "action",
                    label: "",
                    cell: IngresoActionCell
            }];

            this.ingresoPageableCollection = new IngresoPageableCollection();

            var pageableGrid = new Backgrid.Grid({
                columns: columns,
                collection: this.ingresoPageableCollection,
                row: MyRow,
                body: window.Backgrid.SummedColumnBody.extend({ columnsToSum: ['monto'] })
            });

            // Render the grid
            var $example2 = this.$el.find("#example-2-result");
            $example2.append(pageableGrid.render().el)

            // Initialize the paginator
            var paginator = new Backgrid.Extension.Paginator({
                collection: this.ingresoPageableCollection
            });

            // Render the paginator
            $example2.after(paginator.render().el);

            // Initialize a client-side filter to filter on the client
            // mode pageable collection's cache.
            var filter = new Backgrid.Extension.ClientSideFilter({
              collection: this.ingresoPageableCollection,
              fields: ['donadorNombre', 'eventoNombre']
            });

            // Render the filter
            $example2.before(filter.render().el);

            // Add some space to the filter and move it to the right
            $(filter.el).css({float: "right", margin: "20px"});

            // Fetch some data
            this.ingresoPageableCollection.fetch({reset: true});
        }
    });

    return IngresoAdminView;

});