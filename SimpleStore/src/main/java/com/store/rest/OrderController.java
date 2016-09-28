package com.store.rest;

import com.store.control.OrderManager;
import com.store.model.frontmodel.ClientOrder;
import com.store.model.frontmodel.CompleteOrderDetail;
import com.store.model.frontmodel.VisualOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController {

    @Autowired
    private OrderManager orderManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public List<VisualOrderDetail> getOrderDetail(@RequestParam(value = "id")Integer id){
        return orderManager.getVisualOrder(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public void saveOrder(@RequestBody ClientOrder clientOrder){
        orderManager.createOrder(clientOrder);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping (value = "/order/history",method = RequestMethod.GET)
    public List<CompleteOrderDetail> getOrderByDate(@RequestParam(value = "customerId") Integer customerId,
                                                    @RequestParam(value = "initDate")String initDate,
                                                    @RequestParam(value = "endDate") String endDate) throws ParseException {


        return orderManager.getCompleteOrderDetailListByDate(customerId,parseToDate(initDate),parseToDate(endDate));

    }

    private Date parseToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateObj = sdf.parse(date);
        return dateObj;
    }


}
