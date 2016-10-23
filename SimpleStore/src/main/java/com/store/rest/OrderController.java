package com.store.rest;

import com.store.model.ClientCart;
import com.store.model.ClientOrder;

import com.store.model.OrderDetail;

import com.store.repository.CartRepository;
import com.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<OrderDetail> getOrderDetail(@RequestParam(value = "id") Integer id) {
        return orderRepository.findOne(id).getOrderDetail();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void saveOrder(@RequestBody ClientOrder clientOrder) {
        //establecer fecha
        clientOrder.setDateOrder(new Date());
        // crear orden
        orderRepository.save(clientOrder);
        // limpiar carrito
        ClientCart cart = clientOrder.getCustomer().getClientCart();
        cartRepository.delete(cart);

    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order/all", method = RequestMethod.GET)
    public List<ClientOrder> getAllOrders() {
        return (List<ClientOrder>) orderRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order/history", method = RequestMethod.GET)
    public List<ClientOrder> getOrderByDate(@RequestParam(value = "customerId") Integer customerId,
            @RequestParam(value = "initDate") String initDate,
            @RequestParam(value = "endDate") String endDate) throws ParseException {

        return orderRepository.findByCustomerIdAndDateOrderBetween(customerId, parseToDate(initDate), parseToDate(endDate));

    }

    private Date parseToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateObj = sdf.parse(date);
        return dateObj;
    }

}
