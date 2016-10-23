(function () {
        var app = angular.module('app1', []);
        //Main config
        app.config(['$httpProvider', function ($httpProvider) {
            $httpProvider.defaults.useXDomain = true;
            delete $httpProvider.defaults.headers.common['X-Requested-With'];
        }
        ]);

        //Service
        app.service("$service", ['$http', serviceFunction]);

        //main Controller
        app.controller("mainController", ['$service', mainCtrl]);

        //Tab Controller
        app.controller('TabController', function () {
            this.tab = 0;
            this.setTab = function (newValue) {
                this.tab = newValue;
            }

            this.isSet = function (tabname) {
                return this.tab === tabname;
            }
        });
    })();


function serviceFunction($http) {
    var host = '//localhost:8080';
    return {
        getCustomerData: function () {
            return $http.get(host + '/customer/all');
        },
        getProducts: function () {
            return $http.get(host + '/product/all');
        },
        getOrders: function () {
            return $http.get(host + '/order/all');
        },
        setCustomerProducts: function (customerId, pList) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.post(host + '/availableProducts?customerId=' + customerId, JSON.stringify(pList), config);
        },

        addProductToCart: function (customerId, productId, quantity) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            var data = {
                customerId: customerId,
                productId: productId,
                quantity: quantity
            };
            $http.post(host + '/addProductToCart', data, config);
        },

        removeElementFromCart: function (cartDetailId) {
                    var config = {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    };
                    var data = {
                        cartDetailId: cartDetailId,
                    };
        return $http.post(host + '/removeElementFromCart', data, config);
                },

        getCart: function (customerId){
         var config = {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    };
         return  $http.get(host + '/cart?customerId='+customerId, config);
        },

        addOrder: function (order) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            //el controlador spring automaticamente enlaza el objeto
            return $http.post(host + '/order', JSON.stringify(order), config);
        }
    }
}


function mainCtrl($service) {
    scope = this;
    // obtener todos los clientes
    $service.getCustomerData().success(function (response) {
        scope.customerData = response;
    });

    this.getOrders = function(){
            $service.getOrders().success(function (response) {
                scope.orders = response;
                console.log(scope.orders)
            });
    };


    //obtener todos los productos
    $service.getProducts().success(function success(response) {
        scope.products = response;
    });
     // obtener el carrito de compras dado un cliente
     this.getCart = function(customerData){
     $service.getCart(customerData.id).success(function success(response){
     scope.cart = response;
     scope.calculateTotal();
     })};

     // eliminar un elemento de un carrito de compras, elimina el elemento de la base de datos
     // el objeto javascript de la lista de detalle de carrito
     this.removeElementFromCart = function(cartDetail, $index){
        $service.removeElementFromCart(cartDetail.id).success(function success(){
        scope.cart.cartDetail.splice($index,1);
        scope.calculateTotal();
        });
     };

    //añade un producto al carrito de compras de un cliente
    this.addProduct = function (customerData, product, quantity) {
        $service.addProductToCart(customerData.id, product.id, quantity);
    };

    // validar carrito
    this.validateOrder = function (cart) {
        if (cart === undefined)
            return false;
        return true;
    };

    //crear orden
    this.addOrder = function (cart, deliveryAddress, customer) {
        if (this.validateOrder(cart)) {
        //crear la orden como objeto javascript con los mismos atributos de la clase para subirlos en json
        var order = {customer: customer, deliveryAddress: deliveryAddress, orderDetail: cart.cartDetail, total: scope.total};
            $service.addOrder(order).success(function success(){
            scope.cart = null;
            });
        }
    };

    //calcular total, aun no funciona
    this.calculateTotal = function () {
        scope.total = 0;
        angular.forEach(scope.cart.cartDetail, function (value) {
                scope.total = scope.total + value.unit * value.product.price;
        });
    };
}