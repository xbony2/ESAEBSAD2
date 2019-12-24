package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.Utils

class GenerateColorRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!generatecolorredirects"], requiredPermissions = "editor", description = 
		"The generate color redirects will generate redirects to a page based on Minecraft's 16 colors. An example use of this command is !generatecolorredirects %COLOR% Pot; Pot")
	onCommand(Message message){
		def args = Utils.getTwoArguments(message)
		
		if(args != null && args.size == 2){
			["White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", 
				"Purple", "Blue", "Brown", "Green", "Red", "Black"].each {color ->
				wiki.edit(args[0].replaceAll(/%COLOR%/, color), "#REDIRECT [[${args[1]}]]", "Generated color redirect.")
			}
			
			"Done."
		}else
			"Illegal arguments."
	}
}
