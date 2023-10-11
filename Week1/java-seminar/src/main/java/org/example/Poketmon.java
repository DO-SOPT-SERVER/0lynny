package org.example;

import java.lang.invoke.CallSite;

public class Poketmon {
    private String name;
    private PoketmonType type;

    public Poketmon(String name, PoketmonType type) {
        this.name = name;
        this.type = type;
        System.out.println("내 이름은 " + name + "이고" + type + "속성임");
    }

    public Poketmon(String name) {
        this.name = name;
        System.out.println("내 이름은 " + name + "임");
    }

    public Poketmon(PoketmonType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PoketmonType getType() {
        return type;
    }
}
