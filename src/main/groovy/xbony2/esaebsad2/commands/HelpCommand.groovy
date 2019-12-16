package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.ESAEBSAD2;

class HelpCommand implements CommandExecutor {
	@Command(aliases = ["!help"], description = "The help command gives a list of commands.")
	onCommand(){
		def builder = new StringBuilder()
			
		builder.append "```"
		
		ESAEBSAD2.handler.getCommands().each { command ->
			builder.append "\n"
			def annotation = command.getCommandAnnotation()
			def emoji = getPermissionEmoji(annotation)
			
			builder.append "${annotation.usage() ?: annotation.aliases()[0]} | ${emoji} ${annotation.description()}"
		}
		
		builder.append "\n```"
		builder.append "Permissions: 🙂 for everyone, 🚷 for Editors, ☢ for Moderators"
		builder.toString()
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
