package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.Action

class ListActionsDoneByCommandCommand implements CommandExecutor {
	@Command(aliases = ["!listactionsdonebycommand"], description = "The list actions done by command command lists the actions done by the bot by a particular command. With one argument, it will list just the first ten done by that command. Given three arguments, which must the command plus two ints, the second argument will be the action ID that it will start at, and the last will be the action ID it'll stop at it (unless there are more than ten actions between them, in which case, it will only print the first ten starting at the first ID). Note that the first argument (the command name) should not include an exclamation mark. An example use is !listactionsdonebycommand gencolorredirects; 3; 20")
	onCommand(Message message){
		def args = Utils.getThreeArguments(message)
		def ret = new StringBuilder()
		
		if(args == null){
			args = Utils.getOneArgument(message)
			
			if(args == null)
				return "Invalid arguments."
			else
				ret.append(getFirst10(args[0]))
		}else{
			try {
				def startId = Integer.parseInt(args[1])
				def endId = Integer.parseInt(args[2])
				
				if(startId > endId)
					throw new IllegalArgumentException()
				
				ret.append "From ID ${startId} to ${endId}:"
				ret.append "\n```"
				
				ArrayList<Action> actionsOfCommand = ActionHandler.actions.findAll {Action action -> 
					action.commandName.equals(args[0])
				}
				
				if(actionsOfCommand.isEmpty())
					return "There were no actions found."
				
				def i = 0
				actionsOfCommand.each { Action action ->
					if(i < 10 && action.id >= startId && action.id <= endId){
						ret.append "\n${action.format()}"
						i++
					}
				}
				
				ret.append "\n```"
			}catch(IllegalArgumentException e){
				ret.append(getFirst10(args[0]))
			}
		}
	}
	
	private static String getFirst10(String commandName){
		def builder = new StringBuilder()
		builder.append "```"
		
		ArrayList<Action> actionsOfCommand =
			ActionHandler.actions.findAll {Action action -> action.commandName.equals(commandName)}
		
		if(actionsOfCommand.isEmpty())
			return "There were no actions found."
		
		10.times { time -> // Starts at 0
			if(actionsOfCommand.size() > time)
				builder.append "\n${actionsOfCommand[time].format()}"
		}
		
		if(actionsOfCommand.size() > 10)
			builder.append "\n${actionsOfCommand.size() - 10} more..."
		
		builder.append "\n```"
		
		builder.toString()
	}
}
