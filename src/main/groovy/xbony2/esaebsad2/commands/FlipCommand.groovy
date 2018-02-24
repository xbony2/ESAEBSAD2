package xbony2.esaebsad2.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor;

class FlipCommand implements CommandExecutor {
	@Command(aliases = ["!flip"], description = "The flip command flips a coin.")
	onCommand(){
		"The coin flip reveals ${Math.random() > 0.5 ? "heads" : "tails"}."
	}
}

