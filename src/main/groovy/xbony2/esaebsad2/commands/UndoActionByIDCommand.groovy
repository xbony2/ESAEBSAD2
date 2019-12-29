package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.Action

class UndoActionByIDCommand implements CommandExecutor {
	@Command(aliases = ["!undoactionbyid"], requiredPermissions = "moderator", description = "The undo action by ID undoes an action based on the action ID given as an argument.")
	onCommand(Message message){
		def arg = Utils.getOneArgument(message)
		
		try {
			if(arg == null)
				throw new IllegalArgumentException()
			
			def id = Integer.parseInt(arg)
			
			Action toRemove = null
			
			def success = ActionHandler.actions.find { Action action ->
				if(action.id == id){
					def ret = action.undoAction("undoactionbyid")
					toRemove = action
					return ret
				}
			}
			
			if(success){
				ActionHandler.removeAction(toRemove)
				"Done."
			}else
				"The undo was unsucessful. It probably needs to be performed manually."
		}catch(IllegalArgumentException e){
			"Illegal argument/arguments (or lack thereof)."
		}
	}
}
