package com.gestankbratwurst.annotationscheduling;

import com.gestankbratwurst.annotationscheduling.annotations.Scheduled;
import com.gestankbratwurst.annotationscheduling.handling.InvocationScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example plugin
 */
public final class AnnotationScheduling extends JavaPlugin {

  @Override
  public void onEnable() {
    InvocationScheduler.register(this, this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  @Scheduled(initialDelay = 10, frequency = 20)
  public void sayHello() {
    Bukkit.getOnlinePlayers().forEach(pl -> pl.sendMessage("Hello"));
  }

}
