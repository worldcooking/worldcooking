package org.worldcooking.web.worldcooking.history.model;

public class WorldcookingHistoryMessageFragment {

	public enum FragmentType {
		NORMAL, IMPORTANT
	}

	private String link;

	private FragmentType fragmentType;

	private String message;

	public WorldcookingHistoryMessageFragment(String message, String link,
			FragmentType fragmentType) {
		super();
		this.message = message;
		this.link = link;
		if (fragmentType == null) {
			this.fragmentType = FragmentType.NORMAL;
		} else {
			this.fragmentType = fragmentType;
		}
	}

	public WorldcookingHistoryMessageFragment(String message, String link) {
		this(message, link, null);
	}

	public WorldcookingHistoryMessageFragment(String message, FragmentType fragmentType) {
		this(message, null, fragmentType);
	}

	public WorldcookingHistoryMessageFragment(String message) {
		this(message, null, null);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public FragmentType getFragmentType() {
		return fragmentType;
	}

	public void setFragmentType(FragmentType fragmentType) {
		this.fragmentType = fragmentType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
