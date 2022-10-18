package com.jodexindustries.shellexecutor;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ShellExecutor extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("shell").setExecutor((sender, command, label, args) -> {
            if (!sender.hasPermission("shellexecutor.shell")) {
                sender.sendMessage("§cУ вас недостаточно прав!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("§cИспользование: §c/shell (название shell файла)");
                return true;
            }
            String file = args[0];
            String[] var1 = new String[]{file};
            Process var2 = null;
            try {
                var2 = Runtime.getRuntime().exec(var1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
            BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));

            while(true) {
                try {
                    if (!((var3.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\u001b[32m|> \u001b[36m" + var3);
            }
            return true;
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
