package com.mark.es.basic.string;

public class Frog extends Amphibian {
	private Characteristic p = new Characteristic("Croaks");

	public Frog() {
		System.out.println("Frog()");
	}

	public static void main(String[] args) {
		Frog frog = new Frog();
	}
}

class Amphibian extends Animal {
	public Amphibian() {
		System.out.println("Amphibian()");
	}
}

class Animal extends LivingCreature {
	public Animal() {
		System.out.println("Animal()");
	}
	private Characteristic p = new Characteristic("has heart");
}

class LivingCreature {
	LivingCreature() {
		System.out.println("LivingCreature()");
	}
	private Characteristic p = new Characteristic("is alive");
	private Description t = new Description("Basic Living Creature");
}

class Characteristic {
	private String s;

	Characteristic(String s) {
		this.s = s;
		System.out.println("Creating Characteristic " + s);
	}
}

class Description{
	private String s;
	Description(String s){
		this.s = s;
		System.out.println("Creating Description " + s);
	}
}