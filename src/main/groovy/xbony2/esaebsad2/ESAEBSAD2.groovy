package xbony2.esaebsad2

import de.btobastian.sdcf4j.handler.JDA3Handler
import fastily.jwiki.core.*;
import fastily.jwiki.dwrap.*;
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder;
import xbony2.esaebsad2.commands.*

class ESAEBSAD2 {

	static JDA jda
	static JDA3Handler handler
	static Wiki wiki
	
	static main(def args){
		jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking()
		handler = new JDA3Handler(jda)
		handler.registerCommand(new FlipCommand())
		handler.registerCommand(new DevCommand())
		handler.registerCommand(new HelpCommand())
		handler.registerCommand(new ArticleOfTheWeekCommand())
		
		wiki = new Wiki("ftb.gamepedia.com")
		wiki.login(args[1], args[2])
	}
}