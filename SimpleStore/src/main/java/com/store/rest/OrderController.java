package com.store.rest;

import com.store.model.ClientOrder;

import com.store.model.OrderDetail;

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

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<OrderDetail> getOrderDetail(@RequestParam(value = "id") Integer id) {
        return orderRepository.findOne(id).getOrderDetail();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void saveOrder(@RequestBody ClientOrder clientOrder) {
        orderRepository.save(clientOrder);
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
