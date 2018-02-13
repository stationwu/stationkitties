package com.cat.domain;

public enum TertiaryColor {
	BARKBROWN(1,"#886662"),
	CERULIAN(2,"#385877"),
	SCARLET(3,"#ea5f5a"),
	SKYBLUE(4,"#83d5ff"),
	COFFEE(5,"#756650"),
	ROYALPURPLE(6,"#cf5be8"),
	LEMONADE(7,"#ffef85"),
	SWAMPGREEN(8,"#44e192"),
	CHOCOLATE(9,"#c47e33"),
	ROYALBLUE(10,"#5b6ee8"),
	WOLFGREY(0,"#737184");

	private int index;

	private String color;

	private TertiaryColor(int index, String color) {
		this.index = index;
		this.color = color;
	}

	public int getIndex() {
		return index;
	}

	public String getColor() {
		return color;
	}
	
	public static TertiaryColor byOrdinal(int ord) {
        for (TertiaryColor m : TertiaryColor.values()) {
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
