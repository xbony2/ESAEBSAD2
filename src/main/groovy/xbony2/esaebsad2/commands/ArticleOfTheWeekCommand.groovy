package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import fastily.jwiki.core.NS;
import okhttp3.HttpUrl
import xbony2.esaebsad2.ESAEBSAD2

class ArticleOfTheWeekCommand implements CommandExecutor {
	@Command(aliases = ["!articleoftheweek"], description = "The article of the week command decided the article of the week using an extremely advanced algorithm.")
	onCommand(){
		def page = ESAEBSAD2.wiki.getRandomPages(1, NS.MAIN)[0]
		
		"The article of the week is..." + "\n" +
		"${page}! <${HttpUrl.parse("https://ftb.gamepedia.com").newBuilder().addPathSegment(page).build()}>"
	}
}
