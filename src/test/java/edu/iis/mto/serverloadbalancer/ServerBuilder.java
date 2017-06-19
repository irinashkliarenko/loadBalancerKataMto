package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if(initialLoad > 0) {
			addInitialLoad(server);
		}
		
		return server;
	}

	private void addInitialLoad(Server server) {
		int initialVmSize = (int)(initialLoad / (double)capacity * server.MAXIMUM_LOAD);
		Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
		server.addVm(initialVm);
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}

}
