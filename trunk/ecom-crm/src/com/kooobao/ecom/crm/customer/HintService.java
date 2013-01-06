package com.kooobao.ecom.crm.customer;

import java.util.List;

import com.kooobao.common.domain.service.Context;
import com.kooobao.ecom.crm.customer.entity.Hint;
import com.kooobao.ecom.crm.customer.entity.HintFollowup;

public interface HintService {

	/**
	 * Register a Hint
	 * 
	 * @param hint
	 * 
	 */
	Hint addHint(Context context, Hint hint);

	/**
	 * Update Hint info
	 * 
	 * @param context
	 * @param hint
	 */
	Hint update(Context context, Hint hint);

	/**
	 * Record a follow up action. System should try to determine whether this
	 * hint have fulfilled the quality requirement
	 * 
	 * @param hint
	 */
	void followup(Context context, HintFollowup followup);

	/**
	 * Check the suspect hint
	 * 
	 * @param context
	 * @param hint
	 * @param pass
	 *            true to FREE, false to DISCARD
	 */
	void revise(Context context, Hint hint, boolean pass);

	/**
	 * Discard the hint
	 * 
	 * @param context
	 * @param hint
	 */
	void discard(Context context, Hint hint, String comment);

	/**
	 * Get hints list for current user
	 * 
	 * @return
	 */
	List<Hint> getAvailableHints(Context context);

	/**
	 * Request hints to be allocated
	 * 
	 * @return the number of hints as the request result
	 */
	int request(Context context);

	/**
	 * Exchange given hints with new hints from library
	 * 
	 * @param hints
	 * @return count of newly allocated
	 */
	int exchange(Context context, List<Hint> hints);

	/**
	 * Free the hints that over times
	 */
	void freeHints();
}
