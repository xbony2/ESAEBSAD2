package xbony2.esaebsad2

class Utils {
	static void setJDARoles(){
		ESAEBSAD2.jda.getGuilds().each { server ->
			server.getMembersWithRoles(server.getRolesByName("Editor", false)).each { member ->
				ESAEBSAD2.handler.addPermission(member.getUser().getId(), "editor")
			}
		}
	}
}
