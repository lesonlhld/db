package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Invoice implements Serializable {
	private int id;
	private Cart cart;
	private Date invoiceDate;
	private Time invoiceTime;
	private long totalMoney;
	private String voucher;
	private Payment payment;
	
	public Invoice() {
		super();
	}
	public Invoice(int id, Cart cart, long totalMoney, Time invoiceTime, Date invoiceDate, String voucher, Payment payment) {
		super();
		this.id = id;
		this.cart = cart;
		this.totalMoney = totalMoney;
		this.invoiceTime = invoiceTime;
		this.invoiceDate = invoiceDate;
		this.voucher = voucher;
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Time getInvoiceTime() {
		return invoiceTime;
	}
	public void setInvoiceTime(Time invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public Payment getPaymentMethod() { 
		return payment; 
	}
	public void setPaymentMethod(Payment payment) { 
		this.payment = payment; 
	}
}
