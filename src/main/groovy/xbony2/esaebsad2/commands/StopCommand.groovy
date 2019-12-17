package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class StopCommand implements CommandExecutor {
	@Command(aliases = ["!stop"], requiredPermissions = "moderator", description = 
		"The stop command turns off ESAEBSAD.")
	onCommand(){
		System.exit(0)
	}
}
