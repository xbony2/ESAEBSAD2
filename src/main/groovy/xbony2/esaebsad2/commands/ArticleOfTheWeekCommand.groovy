package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.ESAEBSAD2

class ArticleOfTheWeekCommand implements CommandExecutor {
	@Command(aliases = ["!articleoftheweek"], description = "The article of the week command decided the article of the week using an extremely advanced algorithm.")
	onCommand(){
		def page = ESAEBSAD2.wiki.getRandomPages(1)[0]
		
		"The article of the week is..." + "\n" +
		"${page}!"
	}
}
