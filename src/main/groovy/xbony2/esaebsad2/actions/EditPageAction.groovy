package xbony2.esaebsad2.actions

import xbony2.esaebsad2.*
import xbony2.esaebsad2.ActionHandler

class EditPageAction extends Action {
	private String title
	private String prevWikitext
	private String newWikitext
	
	EditPageAction(String commandName, String title, String prevWikitext, String newWikitext){
		super("EditPageAction", commandName)
		
		this.title = title
		this.prevWikitext = prevWikitext
		this.newWikitext = newWikitext
	}

	@Override
	public boolean undoAction(String commandName){
		if(!ESAEBSAD2.wiki.getPageText(title).equals(newWikitext))
			return false; // If the page has been changed since the edit, we can't revert it
		
		ActionHandler.addAction(new EditPageAction(commandName, title, newWikitext, prevWikitext))
		ESAEBSAD2.wiki.edit(title, prevWikitext, "Undoing previous edit (which used ${this.commandName}) using $commandName")
	}

	@Override
	public String format(){
		"${super.format()}\t${Utils.cutTo25(title)}\t${Utils.cutTo25(prevWikitext)}\t${Utils.cutTo25(newWikitext)}";
	}
}
