package com.dardan.rrafshi.scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dardan.rrafshi.commons.Randomizer;


public final class IotScanner
{
	public List<Integer> scanPorts(final String host, final List<Integer> ports)
	{
		final List<Integer> openPorts = Collections.synchronizedList(new ArrayList<>());
		final List<Integer> scannedPorts = new ArrayList<>();

		boolean allPortsScanned = false;

		while(!allPortsScanned) {
			final int port = Randomizer.anyOf(ports);

			if(scannedPorts.size() == ports.size())
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

	public Map<Integer, String> grabBanner(final String host, final List<Integer> ports)
	{
		final Map<Integer, String> banners = Collections.synchronizedMap(new HashMap<>());
		final List<Integer> scannedPorts = new ArrayList<>();

		boolean allPortsScanned = false;

		while(!allPortsScanned) {
			final int port = Randomizer.anyOf(ports);

			if(scannedPorts.size() == ports.size())
				allPortsScanned = true;

			if(scannedPorts.contains(port))
				continue;

			final BannerGrabber grabber = new BannerGrabber(banners, host, port);
			final Thread thread = new Thread(grabber);
			thread.start();

			scannedPorts.add(port);
		}

		return banners;
	}
}
