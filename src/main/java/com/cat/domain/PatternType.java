package com.cat.domain;

public enum PatternType{
	TOTESBASIC(0, "totesbasic"),
	SPOCK(1, "spock"), 
	//TIGERPUNK(2, "tigerpunk"), 
	CALICOOL(2, "calicool"), 
	//LUCKYSTRIPE(4, "luckystripe"), 
	JAGUAR(3,"jaguar");

	private int index;

	private String patternName;

	private PatternType(int index, String patternName) {
		this.index = index;
		this.patternName = patternName;
	}

	public int getIndex() {
		return index;
	}

	public String getPatternName() {
		return patternName;
	}

	public static PatternType byOrdinal(int ord) {
        for (PatternType m : PatternType.values()) {
            if (m.index == ord) {
                return m;
            }
        }
        return null;
    }
	
	public String toString(){
		return this.patternName;
	}

}
