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
		
		wiki = new Wiki("ftb.gamepedia.com")
		wiki.conf.debug = true
		wiki.login(args[1], args[2])
	}
}
