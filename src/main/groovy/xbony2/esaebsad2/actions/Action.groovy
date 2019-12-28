package xbony2.esaebsad2.actions

import java.time.Instant

abstract class Action {
	private final String actionName
	private final String commandName
	private final Instant whenExecuted
	
	/**
	 * This is -1 per default, but will be adjusted once the event is registered.
	 */
	private int id = -1
	
	Action(String actionName, String commandName){
		this.actionName = actionName
		this.commandName = commandName
		whenExecuted = Instant.now()
	}
	
	/**
	 * 
	 * @param commandName the name of the command calling this undoing
	 * @return <code>true</code> if the operation was performed successfully
	 */
	public abstract boolean undoAction(String commandName);
	
	/**
	 * This should be formated in a way that the list actions done commands can print.
	 */
	public String format(){
		"$actionName\t$commandName\t${whenExecuted.toString()}"
	}
}
