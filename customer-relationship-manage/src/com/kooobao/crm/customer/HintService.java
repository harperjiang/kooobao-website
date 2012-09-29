package com.kooobao.crm.customer;

import java.util.List;

import com.kooobao.crm.customer.entity.Hint;
import com.kooobao.crm.order.entity.Order;

public interface HintService {

	/**
	 * Register a Hint
	 * 
	 * @param hint
	 * @return <code>true</code> if success, <code>false</code> if already
	 *         exists
	 */
	boolean addHint(Hint hint);

	/**
	 * Record a follow up action. System should try to determine whether this
	 * hint have fulfilled the quality requirement
	 * 
	 * @param hint
	 */
	void followup(Hint hint);

	/**
	 * Place an order for the potential customer, and this will upgrade the hint
	 * to a customer
	 * 
	 * @param hint
	 * @param order
	 */
	void placeOrder(Hint hint, Order order);

	/**
	 * Get hints list for current user
	 * 
	 * @return
	 */
	List<Hint> getAvailableHints();

	/**
	 * Request hints to be allocated
	 * 
	 * @return the number of hints as the request result
	 */
	int request();

	/**
	 * Exchange given hints with new hints from library
	 * 
	 * @param hints
	 * @return count of newly allocated
	 */
	int exchange(List<Hint> hints);
}
