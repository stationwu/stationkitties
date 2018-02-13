package com.cat.domain;

public enum EyeColor {
	GOLD(0,"#fcdf35"),
	BUBBLEGUM(1,"#ef52d1"),
	LIMEGREEN(2,"#aef72f"),
	CHESTNUT(3,"#a56429"),
	TOPAZ(4,"#0ba09c"),
	MINTGREEN(5,"#43edac"),
	STRAWBERRY(6,"#ef4b62"),
	SIZZURP(7,"#7c40ff");

	private int index;

	private String color;

	private EyeColor(int index, String color) {
		this.index = index;
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public String getColor() {
		return color;
	}
	
	public static EyeColor byOrdinal(int ord) {
        for (EyeColor m : EyeColor.values()) {
            if (m.index == ord) {
                return m;
            }
        }
        return null;
    }
	
	public String toString(){
		return this.color;
	}
}
