package com.kooobao.lm.purchase.entity;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.BasicAddress;
import com.kooobao.lm.profile.entity.Visitor;

@Entity
@Table(name = "lm_purchase")
public class Purchase extends VersionEntity {

	@OneToOne
	@JoinColumn(name = "visitor_id")
	private Visitor visitor;

	@Column(name = "state")
	private String state;

	@Column(name = "delivery_method")
	private String deliveryMethod = DeliveryMethod.SELF_PICK.name();

	@OneToMany(mappedBy = "header", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PurchaseItem> items = new ArrayList<PurchaseItem>();

	@OneToMany(mappedBy = "header", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PurchaseLog> logs = new ArrayList<PurchaseLog>();

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "net_weight")
	private BigDecimal netWeight;

	@Column(name = "comment")
	private String comment;

	@Column(name = "delivery_fee")
	private BigDecimal deliveryFee;

	@Column(name = "discount")
	private BigDecimal discount;

	@Embedded
	private BasicAddress address = new BasicAddress();

	public void request(String comment) {
		Validate.isTrue(StringUtils.isEmpty(state));
		setState(PurchaseState.SUBMIT);

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setToState(PurchaseState.SUBMIT);
		log.setDescription(MessageFormat.format("您的订单已经提交。{0}", comment));
		log.setHeader(this);
		getLogs().add(log);
	}

	public void approve(String operatorId, String comment) {
		Validate.isTrue(getState() == PurchaseState.SUBMIT);
		Validate.isTrue(!StringUtils.isEmpty(operatorId));
		setState(PurchaseState.APPROVE);

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(PurchaseState.SUBMIT);
		log.setToState(PurchaseState.APPROVE);
		log.setOperatorId(operatorId);
		log.setDescription(MessageFormat.format("您的订单已经通过审核。{0}", comment));
		log.setHeader(this);
		getLogs().add(log);
	}

	public void sent(String operatorId, String comment, String deliveryInfo) {
		Validate.isTrue(getState() == PurchaseState.APPROVE);
		Validate.isTrue(!StringUtils.isEmpty(operatorId));
		setState(PurchaseState.SENT);

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(PurchaseState.APPROVE);
		log.setToState(PurchaseState.SENT);
		log.setOperatorId(operatorId);
		log.setDescription(MessageFormat.format("您的订单已经发货，物流信息:{0}。{1}",
				deliveryInfo, comment));
		log.setHeader(this);
		getLogs().add(log);
	}

	public void cancel(String reason) {
		Validate.isTrue(getState() == PurchaseState.APPROVE
				|| getState() == PurchaseState.SUBMIT);

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(getState());
		log.setToState(PurchaseState.CANCEL);
		log.setDescription(MessageFormat.format("您选择取消订单，原因:{0}。", reason));
		log.setHeader(this);
		getLogs().add(log);

		setState(PurchaseState.CANCEL);
	}

	public void reject(String operatorId, String reason) {
		Validate.isTrue(getState() == PurchaseState.SUBMIT);
		Validate.isTrue(!StringUtils.isEmpty(operatorId));
		setState(PurchaseState.REJECT);

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(PurchaseState.SUBMIT);
		log.setToState(PurchaseState.REJECT);
		log.setOperatorId(operatorId);
		log.setDescription(MessageFormat.format("您的订单被拒绝:{0}。", reason));
		log.setHeader(this);
		getLogs().add(log);

	}

	public void interrupt(String operatorId, String reason) {
		Validate.isTrue(!StringUtils.isEmpty(operatorId));

		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(getState());
		log.setToState(PurchaseState.INTERRUPT);
		log.setOperatorId(operatorId);
		log.setDescription(MessageFormat.format("您的订单因故无法完成:{0}。", reason));
		log.setHeader(this);
		getLogs().add(log);

		setState(PurchaseState.INTERRUPT);
	}

	public void addComment(String operatorId, String comment) {
		PurchaseLog log = new PurchaseLog();
		log.setCreateTime(new Date());
		log.setFromState(getState());
		log.setToState(getState());
		log.setOperatorId(operatorId);
		log.setDescription(MessageFormat.format("说明:{0}。", comment));
		log.setHeader(this);
		getLogs().add(log);
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public PurchaseState getState() {
		return PurchaseState.valueOf(state);
	}

	protected void setState(PurchaseState state) {
		this.state = state.name();
	}

	public DeliveryMethod getDeliveryMethod() {
		return DeliveryMethod.valueOf(deliveryMethod);
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod.name();
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void addItem(PurchaseItem item) {
		item.setHeader(this);
		getItems().add(item);
	}

	public List<PurchaseLog> getLogs() {
		return logs;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BasicAddress getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address.setName(address.getName());
		this.address.setLocation(address.getLocation());
		this.address.setPhone(address.getPhone());
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getStateText() {
		return StatusUtils.text(getState());
	}
	
	public String getDeliveryText() {
		return StatusUtils.text(getDeliveryMethod());
	}
}
