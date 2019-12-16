package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class GenerateLanguageCategoriesCommand implements CommandExecutor {
	@Command(aliases = ["!genlangcats"], requiredPermissions = "editor", description = """The generate language 
categories command will generate language-deviation categories based on the wanted categories list.""")
	onCommand(){
		wiki.querySpecialPage("WantedCategories", -1).each { page ->
			println page // TODO
		}
		
		"Done."
	}
}
