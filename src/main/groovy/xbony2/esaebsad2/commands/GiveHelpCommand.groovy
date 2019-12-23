package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.ESAEBSAD2

class GiveHelpCommand implements CommandExecutor {
	@Command(aliases = ["!givehelp"], description = "The give help command helps a confused user")
	onCommand(Message message){
		def arg = Utils.getOneArgument(message)
		def ret = ""
		
		if(arg != null){
			def match = arg =~ /^(<@!\d+>|.+#\d{1,4})$/
			
			if(match.find()){
				if(match.group(1)[0] == ('<' as char))
					ret += "Hello ${match.group(1)}! "
				else{
					def username = Utils.getUserMentionFromTag(match.group(1))
					
					if(username != null)
						ret += "Hello ${username}! "
				}
			}
		}
		// <#342025316442701834> is #help. It's easier to just put the code than to search for it.
		ret += "This Discord is for the Official FTB Wiki, not for FTB or modded Minecraft in general. For better help, you should ask in a Discord devoted for more general purposes, as listed in <#342025316442701834>."
		ret
	}
}
