package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import fastily.jwiki.core.WQuery
import fastily.jwiki.util.FL
import fastily.jwiki.util.GSONP;
import okhttp3.HttpUrl

class FixDoubleRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!fixdoubleredirects"], requiredPermissions = "editor", description = "The fix double redirects command will go through the double redirects list and attempt to automatically fix them.")
	onCommand(){
		wiki.querySpecialPage("", -1).each {
			def target = wiki.getPageText(it).replaceAll("#REDIRECT \\[\\[", "").replaceAll("]]", "")
			def targetsTarget = wiki.getPageText(target).replaceAll("#REDIRECT \\[\\[", "").replaceAll("]]", "")
			wiki.edit(target, "#REDIRECT [[${targetsTarget}]]", "Fixed double redirect.")
		}
		
		"Done."
	}
}
