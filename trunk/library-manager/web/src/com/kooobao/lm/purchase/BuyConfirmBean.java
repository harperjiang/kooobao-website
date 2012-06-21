package com.kooobao.lm.purchase;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.common.NumberFormatter;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.purchase.entity.Purchase;
import com.kooobao.lm.purchase.entity.PurchaseItem;
import com.kooobao.lm.rule.RuleService;

@ManagedBean(name = "buyConfirmBean")
@SessionScoped
public class BuyConfirmBean extends AbstractBean {

	public void onPageLoad() {
		if (null == purchase) {
			Visitor visitor = getCurrentVisitor();
			setAddress(visitor.getAddress());
			BigDecimal discount = getRuleService().getVisitorDiscount(visitor);
			purchase = new Purchase();
			if (!CollectionUtils.isEmpty(selected)) {
				for (Book book : selected) {
					PurchaseItem item = new PurchaseItem();
					item.setBook(book);
					item.setCount(1);
					item.setDiscount(discount);
					purchase.addItem(item);
				}
				selected = null;
			}
			updateAmount();
		}
	}

	public String changeAddress() {
		// Recalculate the delivery fee.
		updateDeliveryFee();
		return "success";
	}

	public String addNewAddress() {
		Visitor visitor = getCurrentVisitor();
		visitor.addAddress(newAddress);
		visitor.setAddress(newAddress);
		getProfileService().saveVisitor(visitor);
		setAddress(newAddress);
		newAddress = new Address();
		updateDeliveryFee();
		return "success";
	}

	public String updateAmount() {
		// Calculate all words
		netWeight = BigDecimal.ZERO;
		bookPrice = BigDecimal.ZERO;
		for (PurchaseItem item : purchase.getItems()) {
			bookPrice = bookPrice.add(item.getBook().getListPrice()
					.multiply(new BigDecimal(item.getCount())));
			netWeight = netWeight.add(item.getBook().getListPrice()
					.multiply(new BigDecimal(item.getCount())));
		}
		// Calculate Delivery Fee
		updateDeliveryFee();

		discount = getRuleService().getDiscount(getCurrentVisitor(), bookPrice,
				deliveryFee);
		setNetWeight(NumberFormatter.format(getNetWeight()));
		setBookPrice(NumberFormatter.format(getBookPrice()));
		setDeliveryFee(NumberFormatter.format(getDeliveryFee()));
		setDiscount(NumberFormatter.format(getDiscount()));
		setTotal(NumberFormatter.format(bookPrice.add(deliveryFee).subtract(
				discount)));
		return "success";
	}

	protected void updateDeliveryFee() {
		if (getPurchase().getDeliveryMethod() == DeliveryMethod.EXPRESS)
			deliveryFee = getRuleService().getDeliveryFee(address, netWeight);
		else
			deliveryFee = NumberFormatter.format(BigDecimal.ZERO);
	}

	public String confirm() {
		if (CollectionUtils.isEmpty(purchase.getItems())) {
			addMessage(FacesMessage.SEVERITY_WARN, "您没有选择书", "您的订单不包含任何书籍");
			return "failed";
		}
		if (null == getAddress()) {
			addMessage(FacesMessage.SEVERITY_WARN, "地址为空", "您没有填写地址信息");
			return "failed";
		}
		updateAmount();

		purchase.setAddress(getAddress());
		purchase.setComment(getComment());
		purchase.setNetWeight(getNetWeight());
		purchase.setTotalPrice(getTotal());
		purchase.setVisitor(getCurrentVisitor());
		purchase.setDiscount(getDiscount());
		purchase.setDeliveryFee(getDeliveryFee());
		
		getPurchaseService().create(purchase);
		return "success";
	}

	private Purchase purchase;

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private Address newAddress = new Address();

	public Address getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private List<Book> selected;

	public List<Book> getSelected() {
		return selected;
	}

	public void setSelected(List<Book> selected) {
		this.selected = selected;
	}

	private Visitor currentVisitor;

	public Visitor getCurrentVisitor() {
		String userId = LoginBean.getCurrentUser();
		if (!StringUtils.isEmpty(userId)
				&& (null == currentVisitor || !currentVisitor.getId().equals(
						userId))) {
			currentVisitor = getProfileService().getVisitor(userId);
		}
		return currentVisitor;
	}

	private BigDecimal deliveryFee;

	private BigDecimal bookPrice;

	private BigDecimal discount;

	private BigDecimal netWeight;

	private BigDecimal total;

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@ManagedProperty("#{ruleService}")
	private RuleService ruleService;

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@ManagedProperty("#{purchaseService}")
	private PurchaseService purchaseService;

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

}
