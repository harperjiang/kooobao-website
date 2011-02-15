package org.harper.frm.ui.jsf.tag;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;

public class UISection extends UIComponentBase implements NamingContainer {
	public static final String COMPONENT_TYPE = "org.harper.ui.jsf.Section";

	public static final String COMPONENT_FAMILY = "org.harper.ui.jsf.Section";

	public UISection() {
		super();
		setRendererType("org.harper.ui.jsf.Section");
	}

	public String getFamily() {
		return (COMPONENT_FAMILY);
	}

	private String link;
}
