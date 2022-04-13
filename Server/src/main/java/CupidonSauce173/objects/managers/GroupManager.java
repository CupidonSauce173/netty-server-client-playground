package CupidonSauce173.objects.managers;

import CupidonSauce173.objects.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupManager {

    private static Map<String, Group> groupMap = new HashMap<>();

    /***
     * Register a Group object in the groupMap.
     * @param group Group object to be registered.
     */
    public static void registerGroup(Group group) {
        groupMap.put(group.name, group);
        System.out.println(group.name + " group has been registered.");
    }

    /**
     * Get the groupMap.
     *
     * @return Map of the groupMap.
     */
    public static Map<String, Group> getGroupMap() {
        return groupMap;
    }

    /**
     * Get the Group object from the group's name.
     *
     * @param name Group's name.
     * @return Group object.
     */
    public static Group getGroup(String name) {
        return groupMap.get(name);
    }


    //region unregisterGroup methods

    /**
     * Unregister a group from the groupMap by Group.
     *
     * @param group Group object to be removed.
     */
    public static void unregisterGroup(Group group) {
        groupMap.remove(group.name);
    }

    /**
     * Unregister a group from the groupMap by group's name.
     *
     * @param name String name of the group.
     */
    public static void unregisterGroup(String name) {
        groupMap.remove(name);
    }
    //endregion
}
