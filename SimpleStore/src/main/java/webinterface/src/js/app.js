angular.module('app1', [])
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
    ])
    .service("$service", ['$http', serviceFunction])
    .controller("mainController", ['$service', mainCtrl]);

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

        addOrder: function (order) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.post(host + '/order', JSON.stringify(order), config);
        }
    }
}


function mainCtrl($service) {
    scope = this;
    $service.getCustomerData().success(function (response) {
        scope.customerData = response;
    });
    $service.getProducts().success(function success(response) {
        scope.products = response;
    });

    this.getProductById = function (id) {
        var product = {};
        angular.forEach(this.products, function (value) {
            if (value.id == id) {
                product = value;
            }
        });
        return product;
    };

    this.addProduct = function (customerData, product, quantity) {
        $service.addProductToCart(customerData.id, product.id, quantity);
    };


    this.setAvailableProducts = function (customerData, pList) {
        if (customerData)
            this.customerData[this.customerData.indexOf(customerData)].products = pList;
        $service.setCustomerProducts(customerData.customer.customer_id, pList);
    };
    this.validateOrder = function (order, total) {
        if (order === undefined || order['customerId'] === undefined || total <= 0)
            return false;
        return true;
    };
    this.addOrder = function (order, total) {
        if (this.validateOrder(order, total)) {
            $service.addOrder(order);
            return true;
        }
        return false;
    };
    this.calculateTotal = function (detail) {
        var total = 0;
        angular.forEach(detail, function (value) {
            if (value.productId > 0) {
                var productPrice = scope.getProductById(value.productId).price;
                total = total + productPrice * value.quantity;
            }
        });
        return total;
    };
}