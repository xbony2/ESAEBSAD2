package xbony2.esaebsad2

class Utils {
	static void setJDARoles(){
		ESAEBSAD2.jda.getGuilds().each {
			it.getMembersWithRoles(it.getRolesByName("Editor", false)).each {
				ESAEBSAD2.handler.addPermission(it.getUser().getId(), "editor")
			}
		}
	}
}
