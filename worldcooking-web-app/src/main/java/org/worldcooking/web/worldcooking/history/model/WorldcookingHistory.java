package org.worldcooking.web.worldcooking.history.model;

import java.util.ArrayList;
import java.util.List;

public class WorldcookingHistory {
	private List<WorldcookingHistoryEntry> historyEntries = new ArrayList<WorldcookingHistoryEntry>();

	public List<WorldcookingHistoryEntry> getHistoryEntries() {
		return historyEntries;
	}

	public void setHistoryEntries(List<WorldcookingHistoryEntry> historyEntries) {
		this.historyEntries = historyEntries;
	}

	public void add(WorldcookingHistoryEntry arg0) {
		historyEntries.add(0, arg0);
	}
}
