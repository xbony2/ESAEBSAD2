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
		handler.with {
			registerCommand(new ArticleOfTheWeekCommand())
			registerCommand(new DevCommand())
			registerCommand(new FixDoubleRedirectsCommand())
			registerCommand(new FlipCommand())
			registerCommand(new HelpCommand())
			registerCommand(new RefreshRolesCommand())
		}
		
		Utils.setJDARoles()
		
		//TODO: resolve https://github.com/fastily/jwiki/issues/5
		wiki = new Wiki(null, null, HttpUrl.parse("https://ftb.gamepedia.com/api.php") as HttpUrl)
		WAction.postAction(wiki, "login", false, [lgname: args[1], lgpassword: args[2], lgtoken: wiki.getTokens(WQuery.TOKENS_LOGIN, "logintoken")])
		wiki.conf.uname = "ESAEBSAD"
		wiki.conf.token = wiki.getTokens(WQuery.TOKENS_CSRF, "csrftoken")
		def wlField = Wiki.getDeclaredField("wl")
		wlField.setAccessible(true)
		wlField.get(wiki).put(wiki.conf.hostname, wiki)
		
		wiki.conf.isBot = wiki.listUserRights(wiki.conf.uname).contains("bot")
		
		// Test zone
		/*def wq = new WQuery(wiki, new WQuery.QTemplate(FL.pMap("list", "querypage", "qppage", "DoubleRedirects"), "results"))
		
		def links = []
		
		while(wq.has()){
			wq.next().listComp("querypage").each {json ->
				links.add(GSONP.getStr(json, "title"))
			}
			
		}
		
		links.each {link -> println(link)}*/
	}
}
