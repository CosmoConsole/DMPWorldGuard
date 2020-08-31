_This release is part of the source code release as outlined in [this announcement](https://www.spigotmc.org/threads/deathmessagesprime.48322/page-53#post-3933244)._

# DMPWorldGuard for DeathMessagesPrime

This plugin adds WorldGuard support to DeathMessagesPrime. Currently this add-on supports blocking messages from players within specific regions. To this end, two new WorldGuard region flags are added: dmp-broadcast-pvp and dmp-broadcast-natural.

These flags are ALLOW by default, but can be denied within any region. If denied, any players dying within those regions will not have their death messages broadcast. dmp-broadcast-pvp can be used to prevent any messages coming from PVP deaths, while dmp-broadcast-natural is used for other death causes. Denying both will suppress all death messages coming from within that region.

Starting from v1.1, you can now also customize messages from within regions by using the dmp-custom-messages flag. Instructions to setting this up can be found [here](https://www.spigotmc.org/threads/dmpworldguard.326890/#post-3148826). 
