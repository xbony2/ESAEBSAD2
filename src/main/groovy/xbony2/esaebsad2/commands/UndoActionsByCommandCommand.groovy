package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.Action

class UndoActionsByCommandCommand implements CommandExecutor {
	@Command(aliases = ["!undoactionsbycommand"], requiredPermissions = "moderator", description = "The undo actions by command undoes an action based on the command that performed that action. Note that the argument (the command name) should not include an exclamation mark.")
	onCommand(Message message){
		def arg = Utils.getOneArgument(message)
		
		if(arg == null)
			"Illegal argument/arguments (or lack thereof)."
		
		ArrayList<Action> toRemove = []
		ArrayList<Action> failedActions = []
			
		ActionHandler.actions.each { Action action ->
			if(action.commandName.equals(arg)){
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
	}
}
