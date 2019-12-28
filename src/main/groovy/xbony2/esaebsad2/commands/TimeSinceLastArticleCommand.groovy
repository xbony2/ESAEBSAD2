package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import java.time.Instant;

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import fastily.jwiki.core.NS;
import fastily.jwiki.dwrap.Contrib
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.Utils

class TimeSinceLastArticleCommand implements CommandExecutor {
	@Command(aliases = ["!timesincelastarticle"], description = "The time since last article command will give the amount of time it has been since the last article that the user (whose username is given as an argument) was created. Doesn't count redirects, vanilla, and disambiguation pages. This command might be very slow and possibly might not work at all if the wiki throws a \"429 Too Many Requests\" error.")
	onCommand(Message message){
		def arg = Utils.getOneArgument(message) ?: "Retep998"
		def ret = ""
		
		//TODO: Find a better way around the 429 Too Many Requests issue, or at least catch it and get it to pause or something
		//TODO: make output not in seconds
		def foundContrib = wiki.getContribs(arg, -1, false, NS.MAIN).find { Contrib contrib ->
			def title = contrib.title
			
			if(arg.equals(wiki.getPageCreator(title))
				&& wiki.getRevisions(title, 1, true, null, null)[0].timestamp.equals(contrib.timestamp)){
					
				def text = wiki.getPageText(title)
					
				if(!text.contains("#REDIRECT")
					&& !text.contains("{{Disambiguation}}")
					&& !text.contains("{{Vanilla")){
						
					def secs = contrib.timestamp.secondsUntil(Instant.now())
						
					ret = "The last article created by $arg is $title, created on ${Utils.formatter.format(contrib.timestamp)}. It has been $secs seconds."
					return true // this will break from the closure
				}
			}
			
			Thread.sleep(250) // hopefully this will stop the 429 Too Many Requests
		}
		
		if(foundContrib == null)
			ret = "No articles found."
		
		ret
	}
}
