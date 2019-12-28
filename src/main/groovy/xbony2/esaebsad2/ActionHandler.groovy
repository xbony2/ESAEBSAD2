package xbony2.esaebsad2

import xbony2.esaebsad2.actions.Action

class ActionHandler {
	private static ArrayList<Action> actions = []
	
	static void addAction(Action action){
		// Doing events.last() returns an Object instead of an Event, annoyingly
		action.id = actions.isEmpty() ? 0 : actions[actions.size - 1].id + 1
		
		actions.add(action)
	}
	
	static ArrayList<Action> getActions(){
		actions
	}
	
	static void removeAction(Action action){
		actions.remove(action)
	}
}
