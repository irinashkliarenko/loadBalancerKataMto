package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

	private int capacity;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		// TODO Auto-generated method stub
		return new Server();
	}

}
