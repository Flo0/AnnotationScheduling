package com.gestankbratwurst.annotationscheduling.handling;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class InvocationScheduler {

  private static final Map<Plugin, InvocationRunnable> PLUGIN_RUNNABLES = new HashMap<>();
  private static final ScheduledMethodScanner METHOD_SCANNER = new ScheduledMethodScanner();

  public static void register(Plugin plugin, Object target) {
    Preconditions.checkArgument(target != null);
    InvocationRunnable runnable = getOrCreateRunnable(plugin);
    List<ScheduledMethod> methodList = METHOD_SCANNER.scanForMethods(target);
    runnable.addAll(methodList);
  }

  private static InvocationRunnable getOrCreateRunnable(Plugin plugin) {
    return PLUGIN_RUNNABLES.computeIfAbsent(plugin, InvocationScheduler::startRunnable);
  }

  private static InvocationRunnable startRunnable(Plugin plugin) {
    InvocationRunnable runnable = new InvocationRunnable();
    Bukkit.getScheduler().runTaskTimer(plugin, runnable, 1, 1);
    return runnable;
  }

}
