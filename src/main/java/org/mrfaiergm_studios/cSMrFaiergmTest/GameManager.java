package org.mrfaiergm_studios.cSMrFaiergmTest;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GameManager {
        // Делаем переменные public static, чтобы к ним можно было обратиться из любого места
        public static HashMap<UUID, String> playerTeams = new HashMap<>();
        public static Set<UUID> playerTPlayers = new HashSet<>();
        public static Set<UUID> playerCTPlayers = new HashSet<>();
        public static boolean startgame = false;
        public static int terorwin = 0;
        public static int conrteorwin = 0;
    }

