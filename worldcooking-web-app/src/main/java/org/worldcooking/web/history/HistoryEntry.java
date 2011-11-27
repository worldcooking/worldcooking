package org.worldcooking.web.history;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.worldcooking.web.history.HistoryMessageFragment.FragmentType;

public class HistoryEntry {
	private Date date;
	private List<HistoryMessageFragment> messageFragments = new ArrayList<HistoryMessageFragment>();
	private String undoLink;

	public HistoryEntry(String undoLink) {
		super();
		this.undoLink = undoLink;
		this.date = new Date();
	}

	public HistoryEntry addMessageFragment(
			HistoryMessageFragment historyMessageFragment) {
		messageFragments.add(historyMessageFragment);
		return this;
	}

	public HistoryEntry addMessageFragment(String message, String link,
			FragmentType fragmentType) {
		return addMessageFragment(new HistoryMessageFragment(message, link,
				fragmentType));
	}

	public HistoryEntry addMessageFragment(String message, String link) {
		return addMessageFragment(message, link, null);
	}

	public HistoryEntry addMessageFragment(String message,
			FragmentType fragmentType) {
		return addMessageFragment(message, null, fragmentType);
	}

	public HistoryEntry addMessageFragment(String message) {
		return addMessageFragment(message, null, null);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUndoLink() {
		return undoLink;
	}

	public void setUndoLink(String undoLink) {
		this.undoLink = undoLink;
	}

	public List<HistoryMessageFragment> getMessageFragments() {
		return messageFragments;
	}

	public void setMessageFragments(
			List<HistoryMessageFragment> messageFragments) {
		this.messageFragments = messageFragments;
	}
}
