/**
 * 
 */
package org.worldcooking.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.worldcooking.server.entity.event.Event;
import org.worldcooking.server.entity.event.Subscription;
import org.worldcooking.server.entity.event.Task;
import org.worldcooking.server.entity.people.Participant;

/**
 * @author MatthieuG
 * 
 */
public class WorldcookingSubStats {

	public WorldcookingSubStats(Event event) {
		// TODO put service.
		List<Subscription> subscriptions = event.getSubscriptions();

		Map<Task, List<String>> taskParticipantMap = new HashMap<Task, List<String>>();
		List<Task> tasksAvailable = event.getAvailableTasks();

		for (Task task : tasksAvailable) {
			taskParticipantMap.put(task, new ArrayList<String>());
		}

		for (Subscription subscription : subscriptions) {
			List<Participant> participants = subscription.getParticipants();
			for (Participant participant : participants) {
				String name = participant.getName();
				Task task = participant.getTask();

			}
		}
	}

}
