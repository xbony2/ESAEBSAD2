package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class FixDoubleRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!fixdoubleredirects"], requiredPermissions = "editor", description = 
		"The fix double redirects command will go through the double redirects list and attempt to automatically fix them.")
	onCommand(){
		wiki.querySpecialPage("DoubleRedirects", -1).each { page ->
			def target = getRedirectTarget(page)
			def targetsTarget = getRedirectTarget(target)
			
			wiki.edit(page, "#REDIRECT [[${targetsTarget}]]", "Fixed double redirect.")
		}
		
		"Done."
	}
	
	private static String getRedirectTarget(String page){
		def match = wiki.getPageText(page) =~ /#REDIRECT( |)\[\[(.+)\]\]/
		
		if(!match.find())
			throw new Exception("Redirect regex failed")
			
		match.group(2)
	}
}
