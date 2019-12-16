package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import xbony2.esaebsad2.*

class RefreshRolesCommand implements CommandExecutor {
	@Command(aliases = ["!refreshroles"], description = """The refresh roles command will update the bot's roles with 
the Discord's roles.""")
	onCommand(){
		Utils.setJDARoles()
		"Done."
	}
}
