package email.com.gmail.cosmoconsole.bukkit.deathmsg.wginterop;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class WorldGuardInterface {
    public static final StateFlag DMP_BROADCAST_PVP = new StateFlag("dmp-broadcast-pvp", true);
    public static final StateFlag DMP_BROADCAST_NATURAL = new StateFlag("dmp-broadcast-natural", true);
    public static final StringFlag DMP_CUSTOM_MSG = new StringFlag("dmp-custom-messages", "");
    /* WorldGuardPlugin */ Object wg6;
    /* WorldGuard */ Object wg7;
    /* RegionContainer */ Object container;
    private int wg_version;
    
    public WorldGuardInterface(int majorVersion) {
        wg_version = majorVersion;
        if (wg_version >= 7) {
            wg7 = com.sk89q.worldguard.WorldGuard.getInstance();
        } else {
            /*
            wg6 = com.sk89q.worldguard.bukkit.WGBukkit.getPlugin();
            container = ((WorldGuardPlugin) wg6).getRegionContainer();
            */
            wg6Error();
        }
    }
    
    public void enable() {
        container = ((com.sk89q.worldguard.WorldGuard) wg7).getPlatform().getRegionContainer();
    }
    
    public void register() {
        if (wg_version >= 7) {
            FlagRegistry registry = ((com.sk89q.worldguard.WorldGuard) wg7).getFlagRegistry();
            try {
                registry.register(DMP_BROADCAST_PVP);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
            try {
                registry.register(DMP_BROADCAST_NATURAL);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
            try {
                registry.register(DMP_CUSTOM_MSG);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
        } else {
            /*
            FlagRegistry registry = ((WorldGuardPlugin) wg6).getFlagRegistry();
            try {
                registry.register(DMP_BROADCAST_PVP);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
            try {
                registry.register(DMP_BROADCAST_NATURAL);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
            try {
                registry.register(DMP_CUSTOM_MSG);
            } catch (FlagConflictException e) {
                e.printStackTrace();
            }
            */
            wg6Error();
        }
    }
    
    private Object wg6Error() {
        throw new RuntimeException("WorldGuard 6 or older requires DMPWorldGuard v1.1 or older");
        //return false;
    }
    
    public boolean getBroadcastPVP(Player p) {
        if (container == null) {
            return true;
        }
        
        if (wg_version >= 7) {
            Location loc = p.getLocation();
            com.sk89q.worldguard.protection.regions.RegionQuery query = ((com.sk89q.worldguard.protection.regions.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(new com.sk89q.worldedit.util.Location(com.sk89q.worldedit.bukkit.BukkitAdapter.adapt(p).getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(p);
            return set.queryState(lp, DMP_BROADCAST_PVP) != State.DENY;
        } else {
            /*
            Location loc = p.getLocation();
            com.sk89q.worldguard.bukkit.RegionQuery query = ((com.sk89q.worldguard.bukkit.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(loc);
            LocalPlayer lp = ((WorldGuardPlugin) wg6).wrapPlayer(p);
            return set.queryState(lp, DMP_BROADCAST_PVP) != State.DENY;
            */
            return wg6Error() == null;
        }
    }
    
    public boolean getBroadcastNatural(Player p) {
        if (container == null) {
            return true;
        }
        
        if (wg_version >= 7) {
            Location loc = p.getLocation();
            com.sk89q.worldguard.protection.regions.RegionQuery query = ((com.sk89q.worldguard.protection.regions.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(new com.sk89q.worldedit.util.Location(com.sk89q.worldedit.bukkit.BukkitAdapter.adapt(p).getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(p);
            return set.queryState(lp, DMP_BROADCAST_NATURAL) != State.DENY;
        } else {
            /*
            Location loc = p.getLocation();
            com.sk89q.worldguard.bukkit.RegionQuery query = ((com.sk89q.worldguard.bukkit.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(loc);
            LocalPlayer lp = ((WorldGuardPlugin) wg6).wrapPlayer(p);
            return set.queryState(lp, DMP_BROADCAST_NATURAL) != State.DENY;
            */
            return wg6Error() == null;
        }
    }

    public String getCustomMsg(Player p) {
        if (container == null) {
            return null;
        }
        
        if (wg_version >= 7) {
            Location loc = p.getLocation();
            com.sk89q.worldguard.protection.regions.RegionQuery query = ((com.sk89q.worldguard.protection.regions.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(new com.sk89q.worldedit.util.Location(com.sk89q.worldedit.bukkit.BukkitAdapter.adapt(p).getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(p);
            return set.queryValue(lp, DMP_CUSTOM_MSG);
        } else {
            /*
            Location loc = p.getLocation();
            com.sk89q.worldguard.bukkit.RegionQuery query = ((com.sk89q.worldguard.bukkit.RegionContainer) container).createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(loc);
            LocalPlayer lp = ((WorldGuardPlugin) wg6).wrapPlayer(p);
            return set.queryValue(lp, DMP_CUSTOM_MSG);
            */
            return wg6Error().toString();
        }
    }
}
