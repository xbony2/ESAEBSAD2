package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.Action

class UndoActionsByIDsCommand implements CommandExecutor {
	@Command(aliases = ["!undoactionsbyids"], requiredPermissions = "moderator", description = "The undo actions by IDs undoes an action based on the action IDs given as arguments. Given two arguments, which must be two ints, the first argument will be the action ID that it will start at, and the last will be the action ID it'll stop at it. The IDs are inclusive.")
	onCommand(Message message){
		def args = Utils.getTwoArguments(message)
		
		try {
			def startId = Integer.parseInt(args[0])
			def endId = Integer.parseInt(args[1])
			
			if(startId > endId)
				throw new IllegalArgumentException()
			
			ArrayList<Action> toRemove = []
			ArrayList<Action> failedActions = []
			
			ActionHandler.actions.each { Action action ->
				if(action.id >= startId && action.id <= endId){
					if(action.undoAction("undoactionsbyids"))
						toRemove.add(action)
					else
						failedActions.add(action)
				}
			}
			
			if(!failedActions.isEmpty()){
				if(toRemove.isEmpty())
					"All undoes were unsucessful. They probably need to be performed manually."
				else{
					def ret = new StringBuilder()
					ret.append "Some actions were undone, but the following failed: "
					
					failedActions.each {Action action -> ret.append "${action.id} "}
					toRemove.each {Action action -> ActionHandler.removeAction(action)}
					
					ret.toString()
				}
			}else{
				if(toRemove.isEmpty())
					"No actions were found."
				else{
					toRemove.each {Action action -> ActionHandler.removeAction(action)}
					"Done."
				}
			}
		}catch(IllegalArgumentException e){
			"Illegal argument/arguments (or lack thereof)."
		}
	}
}
