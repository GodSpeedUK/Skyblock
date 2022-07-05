package tech.moondev.skyblockcore.pet.manager;

public class PetManager {

    public PetManager() {

    }

//    public void increaseLVL(Player player, PetType type, int amount) {
//        UserLevelsData target = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//        if (target.getLevels().containsKey(type.)) {
//            target.getLevels().put(type.name(), target.getLevels().get(type) + amount);
//        } else target.getLevels().put(type.name(), amount);
//
//        Message.PET_LEVELED_UP.send(player,
//                new Placeholder("{petLevel}", java.lang.String.valueOf(target.getLevels().getOrDefault(type, 1))),
//                new Placeholder("{petName}",  Message.PET_NAME.getString()));
//    }
//
//    public void increaseEXP(Player player, PetType type, int amount) {
//        UserLevelsData target = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//        if (target.getLevels().containsKey(type)) {
//            target.getXps().put(type, target.getXps().get(type) + amount);
//        } else target.getXps().put(type, amount);
//
//        // notifications blocker code
//
//        Message.PET_GAINED_EXP.send(player,
//                new Placeholder("{amount}", java.lang.String.valueOf(amount)),
//                new Placeholder("{petName}", type.name()));
//    }
//
//    public void resetEXP(Player player, PetType type) {
//        UserLevelsData target = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//        target.getXps().put(type, 0);
//    }
//
//    public void refreshPet(Player player, PetType type, ChatColor color) {
//        Inventory i = player.getInventory();
//
//        UserLevelsData uld = User.get(player.getUniqueId()).getUserData(UserLevelsData.class);
//        for (ItemStack item : i.getContents()) {
//            if (item == null) continue;
//            if (!item.hasItemMeta()) continue;
//            if (!item.getItemMeta().hasLore()) continue;
//            if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',color + "&l" + Message.PET_NAME.getString().replace("{petName}", type.name())))) continue;
//
//            ItemMeta itemMeta = item.getItemMeta();
//            List<java.lang.String> updatedLore = item.getItemMeta().getLore();
//
//
//
//            updatedLore.set(6, ChatUtil.chat(color + "  ● &lLevel: &f" + uld.getLevels().getOrDefault(type, 1)));
//            updatedLore.set(7, ChatUtil.chat(color + "  ● &lExp: &f" + uld.getXps().getOrDefault(type, 0) + "&7/&f" + "100"));
//            updatedLore.set(8, ChatUtil.chat(color + "  ● &8[" +
//                    ProgressBar.getProgressBar(Math.toIntExact(uld.getXps().get(type)), uld.getMaxBar(), uld.getTotalBar(), '|', ChatColor.GREEN, ChatColor.RED) + "&8]"));
//
//            itemMeta.setLore(updatedLore);
//            item.setItemMeta(itemMeta);
//        }
//    }
}
