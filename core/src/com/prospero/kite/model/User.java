package com.prospero.kite.model;

public class User extends GO {
	String name;
	protected SpaceSystem spaceSystem;

	public User() {
		spaceSystem = new SpaceSystem();
		spaceSystem.generate();
	}
	
	public SpaceSystem getSpaceSystem() {
		return spaceSystem;
	}
}
