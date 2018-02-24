package xbony2.esaebsad2

import de.btobastian.sdcf4j.handler.JDA3Handler
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType
import xbony2.esaebsad2.commands.*

static main(def args){
	def jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking()
	def handler = new JDA3Handler(jda)
	handler.registerCommand(new FlipCommand())
}

