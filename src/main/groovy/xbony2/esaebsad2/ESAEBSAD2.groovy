package xbony2.esaebsad2

import org.fastily.jwiki.core.Wiki
import de.btobastian.sdcf4j.handler.JDA3Handler
import net.dv8tion.jda.core.*
import okhttp3.HttpUrl
import xbony2.esaebsad2.commands.*

class ESAEBSAD2 {

	// For some reason, if these are not public, trying to import statically won't work.
	public static JDA jda
	public static JDA3Handler handler
	public static Wiki wiki
	
	static main(def args){
		//jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking()
		jda = new JDABuilder(AccountType.BOT).setToken(args[0]).build()
		jda.awaitReady()
		
		handler = new JDA3Handler(jda)
		handler.with {
			registerCommand(new ArticleOfTheWeekCommand())
			registerCommand(new DeleteUnusedLanguageCategoriesCommand())
			registerCommand(new DevCommand())
			registerCommand(new FixDoubleRedirectsCommand())
			registerCommand(new FlipCommand())
			registerCommand(new GenerateColorRedirectsCommand())
			registerCommand(new GenerateLanguageCategoriesCommand())
			registerCommand(new GiveHelpCommand())
			registerCommand(new HelpCommand())
			registerCommand(new ListActionsDoneByCommandCommand())
			registerCommand(new ListActionsDoneCommand())
			registerCommand(new RefreshRolesCommand())
			registerCommand(new StopCommand())
			registerCommand(new TimeSinceLastArticleCommand())
			registerCommand(new UndoActionByIDCommand())
			registerCommand(new UndoActionsByCommandCommand())
			registerCommand(new UndoActionsByIDsCommand())
		}
		
		Utils.setJDARoles()
		
		wiki = (new Wiki.Builder())
			.withApiEndpoint(HttpUrl.parse("https://ftb.gamepedia.com/api.php"))
			.withLogin(args[1], args[2])
			.build()
		
	}
}
