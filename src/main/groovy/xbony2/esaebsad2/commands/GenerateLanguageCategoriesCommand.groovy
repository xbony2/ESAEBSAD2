package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.Utils

class GenerateLanguageCategoriesCommand implements CommandExecutor {
	@Command(aliases = ["!genlangcats"], requiredPermissions = "editor", description = 
		"The generate language categories command will generate language-deviation categories based on the wanted categories list.")
	onCommand(){
		wiki.querySpecialPage("Wantedcategories", -1).each { page ->
			def match = page =~ Utils.LANGUAGE_CATEGORY_REGEX
			
			if(match.find())
				wiki.edit(page, "[[Category:${match.group(1)}]]", "Generated language category.")
		}
		
		"Done."
	}
}
