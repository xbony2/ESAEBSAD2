package xbony2.esaebsad2

class Utils {
	
	def static final LANGUAGE_CATEGORY_REGEX = 
		/^Category:(.+)\/([a-z]{2,3}|[a-z]{2,3}-[a-z]{1,9}|[a-z]{2}-[a-z]{1,3}-[a-z]{3}|es-419|simple)$/
	
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
}
