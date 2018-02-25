package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import fastily.jwiki.core.WQuery
import fastily.jwiki.util.FL
import fastily.jwiki.util.GSONP;
import okhttp3.HttpUrl

class FixDoubleRedirectsCommand implements CommandExecutor {
	@Command(aliases = ["!fixdoubleredirects"], /*requiredPermissions = "delete", */description = "The fix double redirects command will go through the double redirects list and attempt to automatically fix them.")
	onCommand(){
		def wq = new WQuery(wiki, new WQuery.QTemplate(FL.pMap("list", "querypage", "qppage", "DoubleRedirects", "qplimit", "max")))
		
		def links = []
		boolean isLastQuery = false;
		while(wq.has()){
			/*links.addAll(FL.toAL(wq.next().listComp("querypage").stream().map(e -> GSONP.getStr(e, "title"))))
			
			links.addAll(wq.next().listComp("querypage").each {e -> GSONP.getStr(e, "title")})*/
			
			wq.next().listComp("querypage").each {json -> links.add(GSONP.getStr(json, "title"))}
			
		}
		
		links.each {link -> println(link)}
	}
}
