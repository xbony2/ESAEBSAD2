package xbony2.esaebsad2

import de.btobastian.sdcf4j.handler.JDA3Handler
import fastily.jwiki.core.*;
import fastily.jwiki.dwrap.*;
import fastily.jwiki.util.FL;
import fastily.jwiki.util.GSONP;
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder;
import okhttp3.HttpUrl
import xbony2.esaebsad2.commands.*

class ESAEBSAD2 {

	static JDA jda
	static JDA3Handler handler
	public static Wiki wiki
	
	static main(def args){
		jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking()
		handler = new JDA3Handler(jda)
		handler.registerCommand(new ArticleOfTheWeekCommand())
		handler.registerCommand(new DevCommand())
		handler.registerCommand(new FixDoubleRedirectsCommand())
		handler.registerCommand(new FlipCommand())
		handler.registerCommand(new HelpCommand())
		
		
		//TODO: resolve https://github.com/fastily/jwiki/issues/5
		wiki = new Wiki(null, null, (HttpUrl)HttpUrl.parse("https://ftb.gamepedia.com/api.php"))
		WAction.postAction(wiki, "login", false, FL.pMap("lgname", args[1], "lgpassword", args[2], "lgtoken", wiki.getTokens(WQuery.TOKENS_LOGIN, "logintoken")))
		wiki.conf.uname = "ESAEBSAD"
		wiki.conf.token = wiki.getTokens(WQuery.TOKENS_CSRF, "csrftoken")
		def wlField = Wiki.class.getDeclaredField("wl")
		wlField.setAccessible(true)
		wlField.get(wiki).put(wiki.conf.hostname, wiki)
		
		wiki.conf.isBot = wiki.listUserRights(wiki.conf.uname).contains("bot")
		
		
		// Test zone
		def wq = new WQuery(wiki, new WQuery.QTemplate(FL.pMap("list", "querypage", "qppage", "DoubleRedirects"), "results"))
		
		def links = []
		
		while(wq.has()){
			/*links.addAll(FL.toAL(wq.next().listComp("querypage").stream().map(e -> GSONP.getStr(e, "title"))))
			
			links.addAll(wq.next().listComp("querypage").each {e -> GSONP.getStr(e, "title")})*/
			
			wq.next().listComp("querypage")/*.each {json ->
				links.add(GSONP.getStr(json, "title"))
			}*/
			
		}
		
		links.each {link -> println(link)}
	}
}
