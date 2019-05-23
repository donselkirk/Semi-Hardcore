package dk.fido2603.semihardcore;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PermissionsManager
{
	private String				pluginName			= "null";
	private SemiHardcore		plugin;
	private Permission			vaultPermission		= null;
    private Chat 				vaultChat 			= null;

	public PermissionsManager(SemiHardcore p)
	{
		this.plugin = p;
		
		RegisteredServiceProvider<Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(Permission.class);
		vaultPermission = permissionProvider.getProvider();
		
		RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        vaultChat = chatProvider.getProvider();
	}

	public void load()
	{
		// Nothing
	}

	public Plugin getPlugin()
	{
		return this.plugin;
	}

	public String getPermissionPluginName()
	{
		return this.pluginName;
	}

	public boolean hasPermission(Player player, String node)
	{
		return vaultPermission.has(player, node);
	}

	public boolean isGroup(String groupName)
	{
		for(String str: vaultPermission.getGroups()) {
		    if(str.contains(groupName))
		       return true;
		}
		return false;
	}

	public String getGroup(String playerName)
	{
		return vaultPermission.getPrimaryGroup(plugin.getServer().getPlayer(playerName));
	}

	public String getPrefix(String playerName)
	{
		Player player = plugin.getServer().getPlayer(playerName);
		return vaultChat.getPlayerPrefix(player);
	}

	public void setGroup(String playerName, String groupName)
	{
		Player player = plugin.getServer().getPlayer(playerName);
		vaultPermission.playerAddGroup(player, groupName);
	}
}