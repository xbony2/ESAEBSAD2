package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.ESAEBSAD2

class GiveHelpCommand implements CommandExecutor {
	@Command(aliases = ["!givehelp"], description = "The give help command helps a confused user")
	onCommand(Message message){
		def args = Utils.getOneArgument(message)
		def ret = ""
		
		if(args != null){
			def match = args =~ /^(<@!\d+>|.+#\d{1,4})/
			
			if(match.find()){
				if(match.group(1)[0] == ('<' as char))
					ret += "Hello ${match.group(1)}! "
				else{ //TODO: turn Hubry#2750 to <@!356912587616813056>
					//println match.group(1)
					//println ESAEBSAD2.jda.getUserById(match.group(1)).getId()
					//ESAEBSAD2.jda.getUsers().each {println "Name: ${it.getName()}; ID: ${it.getId()}"}
					//ESAEBSAD2.jda.getUsers().each {println "Name: ${it.getName()}; ID: ${it.getId()}"}
					//ESAEBSAD2.jda.getUsers().each {user -> user.getDiscriminator()}
					def username = Utils.getUserFromTag(match.group(1))
					
					if(username != null)
						ret += username
				}
			}
		}
		
		ret += "This Discord is for the Official FTB Wiki, not for FTB or modded Minecraft in general. For better help, you should ask in a Discord devoted for more general purposes, as listed in #help."
		ret
	}
}
