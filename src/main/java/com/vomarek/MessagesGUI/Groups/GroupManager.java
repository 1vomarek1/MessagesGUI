package com.vomarek.MessagesGUI.Groups;

import com.vomarek.MessagesGUI.MessagesGUI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroupManager {
    private MessagesGUI plugin;

    private LinkedHashMap<String, Group> groups;

    public GroupManager(MessagesGUI plugin) {
        this.plugin = plugin;

        reloadGroups();
    }

    public GroupManager reloadGroups() {

        groups = new LinkedHashMap<>();

        if (plugin.getConfigFile().get().getConfigurationSection("Groups") == null) return null;

        for (String group : plugin.getConfigFile().get().getConfigurationSection("Groups").getKeys(false)) {
            Group MessageGroup = new Group(group, plugin);
            groups.put(group, MessageGroup);
        }

        sortGroups();
        return this;
    }

    public GroupManager sortGroups() {

        Comparator<Map.Entry<String, Group>> comparator = new Comparator<Map.Entry<String, Group>>() {
            public int compare(Map.Entry<String, Group> o1, Map.Entry<String, Group> o2) {
                return o1.getValue().getPriority() - o2.getValue().getPriority();
            }
        };

        ArrayList<Map.Entry<String, Group>> list = new ArrayList<>(groups.entrySet());

        list.sort(comparator);

        groups = new LinkedHashMap<>();

        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Group> entry = list.get(i);
            groups.put(entry.getKey(), entry.getValue());
        }

        return this;
    }

    public Group getGroup(String name) {
        return groups.getOrDefault(name, null);
    }

    public LinkedHashMap<String, Group> getGroups() {
        return groups;
    }

    public GroupManager deleteGroup(Group group) {
        groups.remove(group.getName());
        plugin.getConfigFile().get().set("Groups." + group.getName(), null);
        plugin.getConfigFile().save();
        group = null;
        sortGroups();
        return this;
    }

    public Group createGroup(String name) {
        plugin.getConfigFile().set("Groups."+name+".Priority",0);
        plugin.getConfigFile().save();
        final Group group = new Group(name, plugin);
        groups.put(name, group);
        sortGroups();
        return group;
    }
}
