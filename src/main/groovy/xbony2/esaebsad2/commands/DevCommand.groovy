package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class DevCommand implements CommandExecutor {
	@Command(aliases = ["!dev"], description = "The dev command gives basic information on ESAEBSAD.")
	onCommand(){
		"""I am a Discord/wiki bot created by Xbony2 in Groovy, using JDA, sdcf4j, and jwiki.
I am open-sourced: <https://github.com/xbony2/ESAEBSAD2>"""
	}
}
