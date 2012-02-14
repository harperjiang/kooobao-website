package vl.model;

import java.util.EventListener;

public interface WorldChangeListener extends EventListener {

	public void worldChanged(WorldChangeEvent event);
}
