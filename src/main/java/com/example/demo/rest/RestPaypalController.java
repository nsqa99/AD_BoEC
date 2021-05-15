package com.example.demo.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.PaypalClient;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/paypal")
public class RestPaypalController {
	
	private final PaypalClient paypalClient;
	
	@Autowired
	public RestPaypalController(PaypalClient paypalClient) {
		this.paypalClient = paypalClient;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
        return paypalClient.createPayment(sum);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request, @RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId){
        return paypalClient.completePayment(request);
    }
	
}
