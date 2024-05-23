package vknue.javaweb.earthstore.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import vknue.javaweb.earthstore.services.CartService;
import vknue.javaweb.earthstore.services.PayPalService;


@Controller
@RequestMapping("/payment")
public class PayPalController {

    private final PayPalService payPalService;
    private final CartService cartService;

    public PayPalController(PayPalService payPalService, CartService cartService) {
        this.payPalService = payPalService;
        this.cartService = cartService;
    }


    @GetMapping("/payPayPal")
    public RedirectView createPayment() {

        try{
            String cancelUrl="http://localhost:8080/payment/cancel";
            String successUrl="http://localhost:8080/payment/success";
            Payment payment = payPalService.createPayment(
                    cartService.getTotalPriceOfItemsInCart(),
                    "USD",
                    "paypal",
                    "sale",
                    cartService.getProductNamesInCsv(),
                    cancelUrl,
                    successUrl);

            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }

        } catch (PayPalRESTException e) {

        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String PaymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            Model model
    ){
        try {
            long transactionId = cartService.commitTransaction("paypal");
            model.addAttribute("id", transactionId);
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")){
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/payment/error";
    }

    @GetMapping("/cancel")
    public String paymentCancel(){
        return "paymentError";
    }


    @GetMapping("/error")
    public String paymentError(){
        return "paymentError";
    }
}