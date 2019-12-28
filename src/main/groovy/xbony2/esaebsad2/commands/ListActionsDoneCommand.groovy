package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.Action

class ListActionsDoneCommand implements CommandExecutor {
	@Command(aliases = ["!listactionsdone"], description = "Lists the actions done by the bot. With no arguments, it will list just the first ten. Given two arguments, which must two ints, the first argument will be the action ID that it will start at, and the last will be the action ID it'll stop at it (unless there are more than ten actions between them, in which case, it will only print the first ten starting at the first ID).")
	onCommand(Message message){
		def args = Utils.getTwoArguments(message)
		def ret = new StringBuilder()
		
		if(ActionHandler.actions.isEmpty())
			return "There were no actions found."
		
		if(args == null){
			ret.append(getFirst10())
		}else{
			try {
				def startId = Integer.parseInt(args[0])
				def endId = Integer.parseInt(args[1])
				
				if(startId > endId)
					throw new IllegalArgumentException()
				
				ret.append "From ID ${startId} to ${endId}:"
				ret.append "\n```"
				
				def i = 0
				ActionHandler.actions.each { Action action ->
					if(i < 10 && action.id >= startId && action.id <= endId)
						ret.append "\n${action.format()}"
				}
				
				ret.append "\n```"
			}catch(IllegalArgumentException e){
				ret.append(getFirst10())
			}
		}
		
		ret.toString()
	}
	
	private static String getFirst10(){
		def builder = new StringBuilder()
		builder.append "```"
		
		10.times { time -> // Starts at 0
			if(ActionHandler.actions.size() > time)
				builder.append "\n${ActionHandler.actions[time].format()}"
		}
		
		if(ActionHandler.actions.size() > 10)
			builder.append "\n${ActionHandler.actions.size() - 10} more..."
		
		builder.append "\n```"
		
		builder.toString()
	}
}
