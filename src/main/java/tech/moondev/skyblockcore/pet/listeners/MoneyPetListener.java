package tech.moondev.skyblockcore.pet.listeners;

import org.bukkit.event.*;

public class MoneyPetListener implements Listener {

//    @EventHandler
//    public void onInteract(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        Action action = event.getAction();
//
//        ItemStack item = player.getInventory().getItemInHand();
//        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//
//        Pet moneyPet = new Pet(ChatColor.GREEN, "iCodeThings", PetType.MONEY, Message.PET_NAME.getString(),
//                ProgressBar.getProgressBar(uld.getXps().get(PetType.MONEY.name()), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED),
//                "additional money while mining");
//
//
//
//        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK)) return;
//
//        if (item == null) return;
//        if (!item.hasItemMeta()) return;
//        if (!item.getItemMeta().hasLore()) return;
//        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(moneyPet.createPet(player).getItemMeta().getDisplayName())) return;
//        if (!(event.getHand() == EquipmentSlot.HAND)) return;
//
//        if (uld.getPetActive().containsKey(player.getUniqueId())) {
//            uld.getPetActive().remove(player.getUniqueId());
//            Message.PET_DISABLED.send(player);
//        } else {
//            uld.getPetActive().put(player.getUniqueId(), PetType.MONEY);
//            Message.PET_ENABLED.send(player);
//        }
//    }
//
//    @EventHandler
//    public void onBlockBreak(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//
//        Block block = event.getBlock();
//        Random rand = new Random();
//        double random = rand.nextInt(100) + 1;
//
//        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//        PetManager petManager = SkyblockCore.getInstance().getPetManager();
//
//        if (!uld.getPetActive().containsValue(PetType.MONEY)) return;
//
//        switch (block.getType()) {
//            case SUGAR_CANE_BLOCK:
//            case CARROT:
//            case POTATO:
//                if (random >= 25) {
//                    if (uld.getXps().get(PetType.MONEY.name()) >= 100) {
//                        petManager.resetEXP(player, PetType.MONEY);
//                        petManager.increaseLVL(player, PetType.MONEY, 1);
//                    }
//                    if (uld.getLevels().get(PetType.MONEY.name()) == 5) return;
//                    petManager.increaseEXP(player, PetType.MONEY, PetType.MONEY.getXpFor(block.getType()));
//                    petManager.refreshPet(player, PetType.MONEY, ChatColor.GREEN);
//                    player.updateInventory();
//
//
//                }
//
//                if (random <= 10) {
//                    SkyblockCore.getInstance().getEcon().depositPlayer(player, 100 * uld.getHoeLevel());
//                }
//                break;
//            default:
//                break;
//        }
//    }

}
