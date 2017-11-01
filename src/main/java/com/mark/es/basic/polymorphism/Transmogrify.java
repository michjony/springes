package com.mark.es.basic.polymorphism;

/**
 * 状态模式示例 使用组合和继承
 */
public class Transmogrify {
	
	public static void main(String[] args) {
		Stage stage = new Stage();
		stage.play();
		stage.change();
		stage.play();
	}
	
}

class Actor {
	public void act() {}
}

class HappyActor extends Actor {
	public void act() {
		System.out.println("HappyActor");
	}
}

class SadActor extends Actor {
	public void act() {
		System.out.println("SadActor");
	}
}

/**
 * 
 * Stage拥有一个Actor的对象，此处使用组合
 */
class Stage {
	private Actor actor = new HappyActor();

	public void change() {
		actor = new SadActor();
	}

	// 字段actor对外隐藏
	// play方法对外
	public void play() {
		actor.act();
	}
}