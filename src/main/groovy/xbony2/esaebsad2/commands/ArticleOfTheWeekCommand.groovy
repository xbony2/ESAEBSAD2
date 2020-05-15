package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import org.fastily.jwiki.core.NS;
import okhttp3.HttpUrl
import xbony2.esaebsad2.ESAEBSAD2
import xbony2.esaebsad2.Utils

class ArticleOfTheWeekCommand implements CommandExecutor {
	@Command(aliases = ["!articleoftheweek"], description = 
		"The article of the week command decides the article of the week using an extremely advanced algorithm.")
	onCommand(){
		def page = ESAEBSAD2.wiki.getRandomPages(1, NS.MAIN)[0]
		
		"""The article of the week is...

||**$page!** <${Utils.linkFromArticle(page)}>||"""
	}
}
