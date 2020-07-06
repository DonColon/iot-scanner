package com.dardan.rrafshi.scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dardan.rrafshi.commons.Randomizer;


public final class IotScanner
{
	public List<Integer> scanPorts(final String host)
	{
		final List<Integer> openPorts = Collections.synchronizedList(new ArrayList<>());
		final List<Integer> scannedPorts = new ArrayList<>();
		boolean allPortsScanned = false;

		while(!allPortsScanned) {
			final int port = Randomizer.generateInteger(Constants.NUMBER_OF_PORTS);

			if(scannedPorts.size() == Constants.NUMBER_OF_PORTS)
				allPortsScanned = true;

			if(scannedPorts.contains(port))
				continue;

			final PortScanner scanner = new PortScanner(openPorts, host, port);
			final Thread thread = new Thread(scanner);
			thread.start();

			scannedPorts.add(port);
		}

		return openPorts;
	}
}
