package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class DeleteUnusedLanguageCategoriesCommand implements CommandExecutor {
	@Command(aliases = ["!deleteunusedlangcats"], requiredPermissions = "editor",
		description = """The delete unused language categories command will deleted unused language categories based on 
the unused categories list.""")
	onCommand(){
		wiki.querySpecialPage("Unusedcategories", -1).each { page ->
			def match = page =~ /^Category:(.+)\/(\w{2}|\w{2}-\w{2}|tl-b)$/
			
			if(match.find())
				wiki.delete(page, "Deleted unused language category.")
		}
		
		"Done."
	}
}
