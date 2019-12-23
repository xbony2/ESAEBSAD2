package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import de.btobastian.sdcf4j.CommandHandler.SimpleCommand
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ESAEBSAD2;
import xbony2.esaebsad2.Utils

class HelpCommand implements CommandExecutor {
	@Command(aliases = ["!help"], description = 
		"The help command gives a list of commands, and specifics given a single parameter.")
	onCommand(Message message){
		def arg = Utils.getOneArgument(message)
		
		if(arg != null){
			SimpleCommand commandRequested = null;
			
			ESAEBSAD2.handler.getCommands().each { command ->
				def annotation = command.getCommandAnnotation()
				def match = (annotation.usage() ?: annotation.aliases()[0]) =~ /!(.+)/
				
				if(!match.find())
					throw new Exception("Failed match with the command name (not suppose to happen).");
				
				if(match.group(1).equals(arg))
					commandRequested = command
			}
			
			if(commandRequested != null){
				def builder = new StringBuilder()
				builder.append "```"
				
				def annotation = commandRequested.getCommandAnnotation()
				def permission = annotation.requiredPermissions()
				def emoji = getPermissionEmoji(annotation)
				
				builder.append "\n${annotation.usage() ?: annotation.aliases()[0]}"
				builder.append "\n"
				builder.append "\n${annotation.description()}"
				builder.append "\n"
				builder.append "\nThis command requires the \"${permission}\" (${emoji}) permission"
				
				builder.append "\n```"
				builder.toString()
			}else{
				"Command not found."
			}
		}else{
			def builder = new StringBuilder()
				
			builder.append "```"
			
			ESAEBSAD2.handler.getCommands().each { command ->
				def annotation = command.getCommandAnnotation()
				def emoji = getPermissionEmoji(annotation)
				
				builder.append "\n${annotation.usage() ?: annotation.aliases()[0]} ${emoji}"
			}
			
			builder.append "\n```"
			builder.append "Permissions: 🙂 for everyone, 🚷 for Editors, ☢ for Moderators"
			builder.toString()
		}
	}
	
	private static String getPermissionEmoji(Command command){
		switch(command.requiredPermissions()){
			case "moderator":
				return "☢"
			case "editor":
				return "🚷"
			default:
				return "🙂"
		}
	}
}
