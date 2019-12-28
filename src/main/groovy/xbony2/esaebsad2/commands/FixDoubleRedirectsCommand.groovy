package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.ActionHandler
import xbony2.esaebsad2.actions.EditPageAction

class FixDoubleRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!fixdoubleredirects"], requiredPermissions = "editor", description = 
		"The fix double redirects command will go through the double redirects list and attempt to automatically fix them.")
	onCommand(){
		wiki.querySpecialPage("DoubleRedirects", -1).each { page ->
			def pageWikitext = wiki.getPageText(page)
			
			def target = getRedirectTarget(pageWikitext)
			def targetsTarget = getRedirectTarget(wiki.getPageText(target))
			
			def newWikitext = "#REDIRECT [[$targetsTarget]]"
			
			ActionHandler.addAction(new EditPageAction("fixdoubleredirects", page, pageWikitext, newWikitext))
			wiki.edit(page, newWikitext, "Fixed double redirect.")
		}
		
		"Done."
	}
	
	private static String getRedirectTarget(String wikitext){
		def match = wikitext =~ /#REDIRECT( |)\[\[(.+)\]\]/
		
		if(!match.find())
			throw new Exception("Redirect regex failed")
			
		match.group(2)
	}
}
