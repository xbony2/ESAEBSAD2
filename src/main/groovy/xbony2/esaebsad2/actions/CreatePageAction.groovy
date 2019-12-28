package xbony2.esaebsad2.actions

import xbony2.esaebsad2.*

class CreatePageAction extends Action {
	String title
	
	CreatePageAction(String commandName, String title){
		super("create", commandName)
		
		this.title = title
	}

	@Override
	public boolean undoAction(String commandName){
		ActionHandler.addAction(new DeletePageAction(commandName, title, ESAEBSAD2.wiki.getPageText(title)))
		ESAEBSAD2.wiki.delete(title, "Undoing previous creation (which used ${this.commandName}) using $commandName")
		true
	}

	@Override
	public String format(){
		"${super.format()}\t${Utils.cutTo25(title)}"
	}
}
