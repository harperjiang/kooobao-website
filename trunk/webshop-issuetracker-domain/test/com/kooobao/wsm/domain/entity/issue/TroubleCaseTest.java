package com.kooobao.wsm.domain.entity.issue;

import static org.junit.Assert.*;

import org.junit.Test;

public class TroubleCaseTest {

	@Test
	public void testGetStatusText() {
		TroubleCase issue = new TroubleCase();
		issue.setStatus(TroubleCase.Status.NEW);
		assertEquals("新建",issue.getStatusText());
	}

}
