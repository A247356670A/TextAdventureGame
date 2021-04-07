package game.items;

import java.util.Map;
import java.util.TreeMap;

public class Item {
    private final String id;
    private final String type;
    private final String name;
    private final String description;
    private final EquipmentLocation position;
    private final Map<String, Integer> properties;

    public Item(String id, String type, String name, String description, Map<String, Integer> properties) {
        this(id, type, name, description, (EquipmentLocation)null, properties);
    }

    public Item(String id, String type, String name, String description, EquipmentLocation position,  Map<String, Integer> properties) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.position = position;
        if (properties != null) {
            this.properties = properties;
        } else {
            this.properties = new TreeMap();
        }

    }
}
