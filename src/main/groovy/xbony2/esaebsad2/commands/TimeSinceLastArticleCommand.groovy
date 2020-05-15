package xbony2.esaebsad2.commands

import static xbony2.esaebsad2.ESAEBSAD2.wiki

import java.time.Instant;

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import org.fastily.jwiki.core.NS;
import org.fastily.jwiki.dwrap.Contrib
import net.dv8tion.jda.core.entities.Message
import xbony2.esaebsad2.Utils

class TimeSinceLastArticleCommand implements CommandExecutor {
	@Command(aliases = ["!timesincelastarticle"], description = "The time since last article command will give the amount of time it has been since the last article that the user (whose username is given as an argument) was created. Doesn't count redirects, vanilla, and disambiguation pages. This command might be very slow and possibly might not work at all if the wiki throws a \"429 Too Many Requests\" error.")
	onCommand(Message message){
		def args = Utils.getOneArgument(message) ?: ["Retep998"]
		def ret = ""
		
		//TODO: make output not in seconds
		def foundContrib = wiki.getContribs(args[0], -1, false, true, NS.MAIN).find { Contrib contrib ->
			def title = contrib.title
			
			if(args[0].equals(wiki.getPageCreator(title))
				&& wiki.getRevisions(title, 1, true, null, null)[0].timestamp.equals(contrib.timestamp)){
					
				def text = wiki.getPageText(title)
					
				if(!text.contains("#REDIRECT")
					&& !text.contains("{{Disambiguation")
					&& !text.contains("{{Vanilla")){
						
					def secs = contrib.timestamp.secondsUntil(Instant.now())
						
					ret = "The last article created by ${args[0]} is $title, created on ${Utils.formatter.format(contrib.timestamp)}. It has been $secs seconds."
					return true // this will break from the closure
				}
			}
		}
		
		if(foundContrib == null)
			ret = "No articles found."
		
		ret
	}
}
