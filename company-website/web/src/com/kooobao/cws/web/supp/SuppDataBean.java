package com.kooobao.cws.web.supp;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;

public class SuppDataBean extends AbstractBean implements JSFStartupAware {

	private List<SelectItem> ages;

	private List<SelectItem> areas;

	private List<SelectItem> interests;

	public List<SelectItem> getAges() {
		return ages;
	}

	public List<SelectItem> getAreas() {
		return areas;
	}

	public List<SelectItem> getInterests() {
		return interests;
	}

	@Override
	public void init() {
		areas = new ArrayList<SelectItem>();
		areas.add(new SelectItem("1", "江浙沪"));
		areas.add(new SelectItem("2", "北京、天津、山东"));
		areas.add(new SelectItem("3", "广东、福建"));
		areas.add(new SelectItem("4", "湖南、湖北、四川、重庆"));
		areas.add(new SelectItem("5", "广西、云南、贵州"));
		areas.add(new SelectItem("6", "东三省"));
		areas.add(new SelectItem("7", "中部地区"));
		areas.add(new SelectItem("8", "西部地区"));

		interests = new ArrayList<SelectItem>();
		interests.add(new SelectItem("Phonics", "Phonics"));
		interests.add(new SelectItem("Phonics", "语言/语法"));
		interests.add(new SelectItem("Phonics", "数学/自然科学"));
		interests.add(new SelectItem("Phonics", "社会科学"));
		interests.add(new SelectItem("Phonics", "歌谣/歌曲"));
		interests.add(new SelectItem("Phonics", "绘本"));
		interests.add(new SelectItem("Phonics", "iPhone/iPad资源"));

		ages = new ArrayList<SelectItem>();
		ages.add(new SelectItem("BABY", "胎教及早期教育(0-12个月)"));
		ages.add(new SelectItem("CHILD", "家庭期幼儿教育(1-3岁)"));
		ages.add(new SelectItem("KINDERGARTEN", "幼儿园教育(3-6岁)"));
		ages.add(new SelectItem("PRIMARY", "小学教育(6-12岁)"));
		ages.add(new SelectItem("MIDDLE", "中学教育(12岁以上)"));
		ages.add(new SelectItem("ADULT", "成人教育"));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
