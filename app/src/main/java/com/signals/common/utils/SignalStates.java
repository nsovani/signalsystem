package com.signals.common.utils;

public enum SignalStates {
	RED(0),YELLOW(1),GREEN(2);
	
	private final int value;
    private SignalStates(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
