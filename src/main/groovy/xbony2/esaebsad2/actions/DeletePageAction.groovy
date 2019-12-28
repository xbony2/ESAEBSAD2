package xbony2.esaebsad2.actions

import xbony2.esaebsad2.*
import xbony2.esaebsad2.ActionHandler

class DeletePageAction extends Action {
	String title
	String wikitext
	
	DeletePageAction(String commandName, String title, String wikitext){
		super("delete", commandName)
		
		this.title = title
		this.wikitext = wikitext
	}

	@Override
	public boolean undoAction(String commandName){
		ActionHandler.addAction(new CreatePageAction(commandName, title))
		ESAEBSAD2.wiki.edit(title, wikitext, "Undoing previous deletion (which used ${this.commandName}) using $commandName")
		true
	}

	@Override
	public String format(){
		"${super.format()}\t${Utils.cutTo25(title)}\t${Utils.cutTo25(wikitext)}";
	}
}
