package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import model.Cart;
import model.CartItem;
import model.OrderStatus;
import model.User;
import model.Invoice;
import model.Payment;
import service.CartItemService;
import service.CartService;
import service.CategoryService;
import service.UserService;
import service.InvoiceService;
import service.PaymentService;
import service.impl.CartServiceImpl;
import service.impl.CartServiceItemImpl;
import service.impl.CategoryServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.InvoiceServiceImpl;
import service.impl.PaymentServiceImpl;
import tools.SendMail;
import util.RandomUUID;

import momo.allinone.models.CaptureMoMoResponse;
import momo.allinone.processor.allinone.CaptureMoMo;
import momo.shared.sharedmodels.Environment;
import momo.shared.utils.LogUtils;

@WebServlet(urlPatterns = "/member/order")
public class OrderController extends HttpServlet {
	UserService userService = new UserServiceImpl();
	CartService cartService = new CartServiceImpl();
	InvoiceService invoiceService = new InvoiceServiceImpl();
	CartItemService cartItemService = new CartServiceItemImpl();
	PaymentService paymentService = new PaymentServiceImpl();
	long time = System.currentTimeMillis();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		User buyer = (User) obj;
		Cart cart = new Cart();
		cart.setBuyer(buyer);
		cart.setBuyTime(new java.sql.Time(time));
		cart.setBuyDate(new java.sql.Date(time));
		cartService.insert(cart);
		Invoice invoice = new Invoice();
		invoice.setCart(cart);
		invoice.setInvoiceTime(new java.sql.Time(time));
		invoice.setInvoiceDate(new java.sql.Date(time));
		
		LogUtils.init();
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = "SFCS-" + getAlphaNumericString(10);
        long amount = 0;
        
        String orderInfo = "Thanh toán với MoMo";
        String returnURL = "http://localhost:9090/SFCS";
        String notifyURL = "http://localhost:9090/SFCS";
        String extraData = "";
        // String bankCode = "SML";

		Object objCart = session.getAttribute("cart");
		if (objCart != null) {
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) objCart;
			for (CartItem cartItem : map.values()) {
				cartItem.setCart(cart);
				amount+=cartItem.getUnitPrice()*cartItem.getQuantity()*(100-cartItem.getProduct().getDiscount())/100;
				cartItemService.insert(cartItem);
			}
		}
		
        Environment environment = Environment.selectEnv("dev", Environment.ProcessType.PAY_GATE);
        
        CaptureMoMoResponse captureMoMoResponse;
		try {
			//captureMoMoResponse = CaptureMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, extraData);
	        //String url = captureMoMoResponse.getPayUrl();
			invoice.setVoucher(null);
			invoice.setPaymentMethod(paymentService.get(2));
			invoice.setTotalMoney(amount);
			invoiceService.insert(invoice);
			
	        session.removeAttribute("cart");
	        resp.sendRedirect(returnURL);
	        //resp.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	static String getAlphaNumericString(int n) { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
