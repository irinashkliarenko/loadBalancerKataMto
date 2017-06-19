package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.attribute.AclEntry.Builder;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}
	
	@Test
	public void balancingServerWithNoVms_serverStayEmpty() {
		Server theServer = a(ServerBuilder.server().withCapacity(1));
		
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		 
		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoadOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneCapacity_andOneSlotVm_fillsServerWithTheVm() {
		Server theServer = a(ServerBuilder.server().withCapacity(1));
		Vm theVm = a(VmBuilder.vm().ofSize(1));
		balancing(aServerListWith(theServer), aVmsListWith(theVm));
		 
		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}
	
	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}
	
	private Vm a(VmBuilder builder) {
		return builder.build();
	}
}
