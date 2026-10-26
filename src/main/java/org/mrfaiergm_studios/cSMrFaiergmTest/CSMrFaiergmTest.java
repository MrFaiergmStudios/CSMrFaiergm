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

public final class CSMrFaiergmTest extends JavaPlugin implements Listener{
    private HashMap<UUID, String> playerTeams = new HashMap<>();
    private Set<UUID> playerTPlayers = new HashSet<>();
    private Set<UUID> playerCTPlayers = new HashSet<>();
    private Set<UUID> playerCutscene = new HashSet<>();
    private BukkitTask activatedbomb = null;
    private int defuswcount = 10;
    private int secont_to_bomb_push = 20;
    boolean cut_scene = false;
    boolean startgame = false;
    int round = 10;
    public int terorwin = 0;
    public int conrteorwin = 0;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("t").setExecutor(this);
        getCommand("ct").setExecutor(this);
        getCommand("start").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerNick = event.getPlayer().getName();
        playerCutscene.add(player.getUniqueId());
        Location startgame_location = new Location(player.getWorld(), -98, 18, 32);
        player.sendTitle("§lWeclome to Mini Game", "By MrFaiergm", 40, 100,40);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,29,  180));
        cut_scene = true;
        new BukkitRunnable(){
            @Override
            public void run(){
                cut_scene = false;
                player.teleport(startgame_location);
                playerCutscene.remove(player.getUniqueId());
            }
        }.runTaskLater(this, 180L);
    }
    @EventHandler
    public void PlayerMovement(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(cut_scene == true){
            if (playerCutscene.contains(player.getUniqueId())){
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
        }

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("t")){
            if(playerTPlayers.size() > playerCTPlayers.size()){
                player.sendMessage("Команда тррористов заполнена! Присоеденитесь к команде спецназа.");
            }
            else if (playerTPlayers.size() < playerCTPlayers.size() || playerCTPlayers.size() == playerTPlayers.size()){
                playerTeams.put(player.getUniqueId(), "T");
                playerTPlayers.add(player.getUniqueId());
                player.sendMessage("Вы успешно присоеднились к команде Террористов!");
                player.setPlayerListName("§c§lТеррорист " + player.getName());
                if (playerCTPlayers.contains(player.getUniqueId()) == playerTPlayers.contains(player.getUniqueId())){
                    playerCTPlayers.remove(player.getUniqueId());
                }
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("ct")){
            if(playerCTPlayers.size() > playerTPlayers.size()){
                player.sendMessage("команда контр терористов заполнена! Присоеденитесь к противоположной команде либо повторите попытку позже");
            } else if (playerCTPlayers.size() < playerTPlayers.size() || playerCTPlayers.size() == playerTPlayers.size()){
                playerCTPlayers.add(player.getUniqueId());
                playerTeams.put(player.getUniqueId(), "Ct");
                player.sendMessage("Вы успешно присоединились к косманде спецназа!");
                player.setPlayerListName("§l§1Спецназ " + player.getName());
                if(playerCTPlayers.contains(player.getUniqueId()) == playerTPlayers.contains(player.getUniqueId())){
                    playerTPlayers.remove(player.getUniqueId());
                }

            }

            return true;
        }
        if (command.getName().equalsIgnoreCase("start")){
            startgame = true;
            Location spawnT = new Location(player.getWorld(), -7, 9, -2);
            Location spawnCT = new Location(player.getWorld(), -54, 3, 81);

            for(Player onlinePlayer : getServer().getOnlinePlayers()){
                String team = playerTeams.get(onlinePlayer.getUniqueId());
                if(team == null){
                    onlinePlayer.sendMessage("Игра началась но ты не выбрал команду");
                    continue;
                }

                if(team.equals("T")){

                    onlinePlayer.sendMessage("Вы телепортировались на базу!");
                    onlinePlayer.teleport(spawnT);
                    onlinePlayer.getInventory().clear();
                    ItemStack attack = new ItemStack(Material.BOW);
                    ItemStack patron = new ItemStack(Material.ARROW, 150);
                    ItemStack bew = new ItemStack(Material.COOKED_BEEF, 120);
                    onlinePlayer.getInventory().addItem(attack);
                    onlinePlayer.getInventory().addItem(patron);
                    onlinePlayer.getInventory().addItem(bew);
                    onlinePlayer.getWorld().dropItem(onlinePlayer.getLocation(),new ItemStack(Material.YELLOW_WOOL));
                    continue;
                }

                if (team.equals("Ct")){
                    onlinePlayer.sendMessage("Вы телепортировались на базу!");
                    onlinePlayer.teleport(spawnCT);
                    onlinePlayer.getInventory().clear();
                    ItemStack attack = new ItemStack(Material.BOW);
                    ItemStack patron = new ItemStack(Material.ARROW, 150);
                    ItemStack bew = new ItemStack(Material.COOKED_BEEF, 120);
                    onlinePlayer.getInventory().addItem(attack);
                    onlinePlayer.getInventory().addItem(patron);
                    onlinePlayer.getInventory().addItem(bew);

                    onlinePlayer.getWorld().dropItem(onlinePlayer.getLocation(),new ItemStack(Material.SHEARS));
                }


            }
        }
        return false;
    }
    @EventHandler
    public void bomb_set(BlockPlaceEvent event){
        Player player = event.getPlayer();
        Block blockPlacedBomd = event.getBlock();
        Block blockisplacedbomd = event.getBlockAgainst();
        String playerNick = event.getPlayer().getName();
        Location getCenter = blockPlacedBomd.getLocation();
        if (blockPlacedBomd.getType() == Material.YELLOW_WOOL && blockisplacedbomd.getType() == Material.OBSIDIAN){
            String playerIs = playerTeams.get(player.getUniqueId());
            if(startgame == true){
                if (playerIs.equals("T") && playerIs != null){
                    Bukkit.broadcastMessage("§lИгрок " + playerNick + "§l§c§nЗаложил Бомбу!!!");
                    activatedbomb = new BukkitRunnable(){
                        @Override
                        public void run(){
                            if (secont_to_bomb_push >= 0){
                                Bukkit.broadcastMessage("§l§cДо Взрыва Осталось " + secont_to_bomb_push);
                                secont_to_bomb_push--;
                            }if (secont_to_bomb_push <= 0){
                                Bukkit.broadcastMessage("§lБомба была " + "§l§cВзорвана.");
                                blockPlacedBomd.setType(Material.AIR);
                                terorwin++;
                                startgame = false;
                                defuswcount = 10;
                                secont_to_bomb_push = 20;
                                for(Player p : getServer().getOnlinePlayers()){
                                    p.sendTitle("§l§cТерористы победили", "§lвсего побед - " + terorwin, 20,180,20);


                                }
                                int pereodic = 1;
                                for(int x = -3; x <= 3; x++){
                                    pereodic = pereodic + 4;
                                    for(int z = -3; z <= 3; z++){
                                        Location spawn = getCenter.clone().add(x, 0, z);
                                        new BukkitRunnable(){
                                            @Override
                                            public void run(){
                                                player.getWorld().strikeLightning(spawn);

                                            }
                                        }.runTaskLater(CSMrFaiergmTest.this, pereodic);
                                    }
                                }
                                int pereodic2 = 4;
                                for (int x = -30; x <= 30; x++){
                                    pereodic2 = pereodic2 + 2;
                                    for (int z = -30; z <= 30; z++){
                                        Location spawn = getCenter.clone().add(x, 0, z);
                                        new BukkitRunnable(){
                                            @Override
                                            public void run(){
                                                player.getWorld().strikeLightning(spawn);
                                            }
                                        }.runTaskLater(CSMrFaiergmTest.this, pereodic2);
                                    } this.cancel();
                                }

                            }

                        }

                    }.runTaskTimer(CSMrFaiergmTest.this, 0L, 20L);

                }
                else if (playerIs == null || playerIs.equals("Ct")){
                    player.sendMessage("Вы не выбрали команду! либо вы Спецназ");
                    event.setCancelled(true);

                }
            } else if(startgame != true){
                player.sendMessage("Игра еще не началась!");
                if(blockisplacedbomd.getType() == Material.OBSIDIAN) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }
    @EventHandler
    public void bomb_razmin(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location Block_location = event.getBlock().getLocation().add(0,-1,0);
        ItemStack iteminhand = player.getInventory().getItemInMainHand();
        String playeris = playerTeams.get(event.getPlayer().getUniqueId());
        if(startgame == true){
            if (playeris.equals("Ct") && playeris != null){
                if (block.getType() == Material.YELLOW_WOOL && iteminhand.getType() == Material.SHEARS && Block_location.getBlock().getType() == Material.OBSIDIAN){
                    if (defuswcount <= 10){
                        event.setCancelled(true);
                        player.sendMessage("Осталось разминировать  - " + defuswcount);
                        defuswcount--;
                    }
                    if (defuswcount <= 0){
                        block.setType(Material.AIR);
                        if (activatedbomb != null){
                            activatedbomb.cancel();
                            activatedbomb = null;
                            defuswcount = 10;
                            conrteorwin++;
                            startgame = false;
                            secont_to_bomb_push = 20;
                            for(Player p : getServer().getOnlinePlayers()){
                                p.sendTitle("§l§1Спецназ Выйграл!", "§lвсего побед - " + conrteorwin, 20,180,20);

                            }


                        }
                    }

                } else if (playeris.equals("Ct") && iteminhand.getType() != Material.SHEARS && block.getType() == Material.YELLOW_WOOL){
                    player.sendMessage("Бомба Дефьюзиться только через ножницы!");
                    event.setCancelled(true);
                }
            } else if(playeris.equals("T") && iteminhand.getType() != Material.SHEARS && block.getType() == Material.YELLOW_WOOL){
                player.sendMessage("Нельзя раминировать свою же бомбу");
                event.setCancelled(true);
            }
        } else if (startgame != true && iteminhand.getType() != Material.SHEARS && block.getType() == Material.YELLOW_WOOL){
            player.sendMessage("Игра еще не начата!");
            event.setCancelled(true);
        }
    }


}
