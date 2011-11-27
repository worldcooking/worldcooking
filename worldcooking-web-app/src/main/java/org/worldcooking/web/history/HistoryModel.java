package org.worldcooking.web.history;

import java.util.ArrayList;
import java.util.List;

public class HistoryModel {
	private List<HistoryEntry> historyEntries = new ArrayList<HistoryEntry>();

	public List<HistoryEntry> getHistoryEntries() {
		return historyEntries;
	}

	public void setHistoryEntries(List<HistoryEntry> historyEntries) {
		this.historyEntries = historyEntries;
	}

	public void add(HistoryEntry arg0) {
		historyEntries.add(0, arg0);
	}
}
