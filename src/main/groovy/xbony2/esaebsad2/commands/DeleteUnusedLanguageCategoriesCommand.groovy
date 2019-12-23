package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.Utils

class DeleteUnusedLanguageCategoriesCommand implements CommandExecutor {
	@Command(aliases = ["!deleteunusedlangcats"], requiredPermissions = "editor", description =
		"The delete unused language categories command will delete unused language categories. These categories usually become unused through changes in the category of the untranslated page.")
	onCommand(){
		wiki.querySpecialPage("Unusedcategories", -1).each { page ->
			def match = page =~ Utils.LANGUAGE_CATEGORY_REGEX
			
			if(match.find())
				wiki.delete(page, "Deleted unused language category.")
		}
		
		"Done."
	}
	
	
}
