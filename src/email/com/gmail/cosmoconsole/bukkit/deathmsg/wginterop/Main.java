package email.com.gmail.cosmoconsole.bukkit.deathmsg.wginterop;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import email.com.gmail.cosmoconsole.bukkit.deathmsg.DeathMessageCustomEvent;
import email.com.gmail.cosmoconsole.bukkit.deathmsg.DeathMessagesPrime;

public class Main extends JavaPlugin implements Listener {
    DeathMessagesPrime dmp;
    WorldGuardInterface wg;
    
    @Override
    public void onLoad() {
        wg = new WorldGuardInterface(Integer.parseInt(Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion().split("\\.")[0]));
        wg.register();
    }
    
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents((Listener)this, (Plugin)this);
        dmp = (DeathMessagesPrime) Bukkit.getPluginManager().getPlugin("DeathMessagesPrime");
        wg.enable();
    }
    
    @EventHandler
    private void checkIfMessageShouldAppear(DeathMessageCustomEvent evt) {
        if (evt.getPlayer() != null) {
            if (evt.isPVP() && !wg.getBroadcastPVP(evt.getPlayer())) {
                evt.setCancelled(true);
            } else if (!evt.isPVP() && !wg.getBroadcastNatural(evt.getPlayer())) {
                evt.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    private void onDMPMessage(DeathMessageCustomEvent evt) {
        if (evt.getPlayer() != null) {
            String s = wg.getCustomMsg(evt.getPlayer());
            if (s != null && !s.isEmpty()) {
                evt.setTag("wgcustom." + s + "." + evt.getTag());
            }
        }
    }
}
