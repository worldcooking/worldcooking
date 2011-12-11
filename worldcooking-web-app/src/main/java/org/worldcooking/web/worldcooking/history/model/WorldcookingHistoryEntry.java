package org.worldcooking.web.worldcooking.history.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.worldcooking.web.worldcooking.history.model.WorldcookingHistoryMessageFragment.FragmentType;

public class WorldcookingHistoryEntry {
	private Date date;
	private List<WorldcookingHistoryMessageFragment> messageFragments = new ArrayList<WorldcookingHistoryMessageFragment>();
	private String undoLink;

	public WorldcookingHistoryEntry(String undoLink) {
		super();
		this.undoLink = undoLink;
		this.date = new Date();
	}

	public WorldcookingHistoryEntry addMessageFragment(
			WorldcookingHistoryMessageFragment historyMessageFragment) {
		messageFragments.add(historyMessageFragment);
		return this;
	}

	public WorldcookingHistoryEntry addMessageFragment(String message, String link,
			FragmentType fragmentType) {
		return addMessageFragment(new WorldcookingHistoryMessageFragment(message, link,
				fragmentType));
	}

	public WorldcookingHistoryEntry addMessageFragment(String message, String link) {
		return addMessageFragment(message, link, null);
	}

	public WorldcookingHistoryEntry addMessageFragment(String message,
			FragmentType fragmentType) {
		return addMessageFragment(message, null, fragmentType);
	}

	public WorldcookingHistoryEntry addMessageFragment(String message) {
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

	public List<WorldcookingHistoryMessageFragment> getMessageFragments() {
		return messageFragments;
	}

	public void setMessageFragments(
			List<WorldcookingHistoryMessageFragment> messageFragments) {
		this.messageFragments = messageFragments;
	}
}
