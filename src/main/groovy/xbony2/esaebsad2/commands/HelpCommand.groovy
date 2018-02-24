package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.ESAEBSAD2;

class HelpCommand implements CommandExecutor {
	@Command(aliases = ["!help"], description = "The help command gives a list of commands.")
	onCommand(){
		def builder = new StringBuilder()
			
		builder.append "```"
			
		ESAEBSAD2.handler.getCommands().each {command ->
			builder.append "\n"
				
			def usage = command.getCommandAnnotation().usage()
			if(usage.isEmpty())
				usage = command.getCommandAnnotation().aliases()[0] // if there's not a defined usage the alias will be used.
			
			builder.append "${usage} | ${command.getCommandAnnotation().description()}"
		}
		
		builder.append "\n```"
		builder.toString()
	}
}
