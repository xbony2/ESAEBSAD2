package xbony2.esaebsad2

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

import net.dv8tion.jda.core.entities.Message
import okhttp3.HttpUrl

class Utils {
	
	def static final LANGUAGE_CATEGORY_REGEX = 
		/^Category:(.+)\/([a-z]{2,3}|[a-z]{2,3}-[a-z]{1,9}|[a-z]{2}-[a-z]{1,3}-[a-z]{3}|es-419|simple)$/
	
	def static final DateTimeFormatter formatter =
		DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.US).withZone(ZoneId.systemDefault())
	
	static void setJDARoles(){
		ESAEBSAD2.jda.getGuilds().each { server ->
			server.getMembersWithRoles(server.getRolesByName("Editor", false)).each { member ->
				ESAEBSAD2.handler.addPermission(member.getUser().getId(), "editor")
			}
			
			server.getMembersWithRoles(server.getRolesByName("Moderator", false)).each { member ->
				ESAEBSAD2.handler.addPermission(member.getUser().getId(), "moderator")
			}
		}
	}
	
	// TODO: perhaps put into one method
	static String getOneArgument(Message message){
		def match = message.getContentRaw() =~ /![a-z]+ (.+)/
		
		match.find() ? match.group(1) : null
	}
	
	static ArrayList<String> getTwoArguments(Message message){
		def match = message.getContentRaw() =~ /![a-z]+ (.+); (.+)/
		
		match.find() ? [match.group(1), match.group(2)] : null
	}
	
	static ArrayList<String> getThreeArguments(Message message){
		def match = message.getContentRaw() =~ /![a-z]+ (.+); (.+); (.+)/
		
		match.find() ? [match.group(1), match.group(2), match.group(3)] : null
	}
	
	static String getUserMentionFromTag(String tag){
		def match = tag =~ /^(.+)#(\d{1,4})$/
		String ret = null
		
		if(match.find())
			ESAEBSAD2.jda.getUsers().each {user -> 
				if(match.group(1).equals(user.getName()) && match.group(2).equals(user.getDiscriminator()))
					ret = user.getAsMention()
			}
		
		ret
	}
	
	static String linkFromArticle(String article){
		HttpUrl.parse("https://ftb.gamepedia.com").newBuilder().addPathSegment(article).build()
	}
	
	static String cutTo25(String string){
		string.length() >= 25 ? "${string.substring(0, 25)}..." : string
	}
}
