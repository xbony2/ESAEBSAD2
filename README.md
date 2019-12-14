# ESAEBSAD2
A Discord bot used by the Official FTB Wiki (mostly me) to accomplish various automated tasks. Kind of an experiment as of right now.

## Setup
A [Discord application token](https://discordapp.com/developers/applications) should already be generated, and a [bot login and password](https://ftb.gamepedia.com/Special:BotPasswords) should be generated (assuming you are using Gamepedia; if another service is used, it's probably just the username and password for the wikiLogin and wikiToken).

A `gradle.properties` file must be created with the following properties:
* `discordToken`: the Discord bot account's token.
* `wikiLogin`: the wiki bot's login. This is NOT the same as the bot's username on Gamepedia, although that's a part of it. This is viewable on [Special:BotPasswords](https://ftb.gamepedia.com/Special:BotPasswords).
* `wikiToken`: the wiki bot's token. This is generated by [Special:BotPasswords](https://ftb.gamepedia.com/Special:BotPasswords). Note when generated, it cannot be viewed again, so make sure to put it in the properties file so it isn't lost. If forgotten a new bot password can be created so not a biggie.

An `example_gradle.properties` will show an example of this configuration (without the confidential information, of course).

After configured, the commands `gradlew build` will compile the code and grab all of the necessary libraries, and `gradlew run` will run the bot. If the code is compiled, it doesn't need to be re-compiled, but if changes are made to the code, then it needs to be re-compiled.

For development, I have an Eclipse run configuration that is a Gradle task of the tasks `build` and `run`.

## Usage
Just run the command `!help` and it will list all of the commands and how to use them. ezpz.
