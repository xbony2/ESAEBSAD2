package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.Utils
import xbony2.esaebsad2.actions.CreatePageAction

class GenerateColorRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!gencolorredirects"], requiredPermissions = "editor", description = 
		"The generate color redirects will generate redirects to a page based on Minecraft's 16 colors. An example use of this command is !generatecolorredirects %COLOR% Pot; Pot")
	onCommand(Message message){
		def args = Utils.getTwoArguments(message)
		
		if(args != null && args.size == 2){
			["White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", 
				"Purple", "Blue", "Brown", "Green", "Red", "Black"].each {color ->
				def title = args[0].replaceAll(/%COLOR%/, color)
				
				ActionHandler.addAction(new CreatePageAction("gencolorredirects", title))
				wiki.edit(title, "#REDIRECT [[${args[1]}]]", "Generated color redirect.")
			}
			
			"Done."
		}else
			"Illegal arguments."
	}
}
