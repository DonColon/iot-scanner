package com.dardan.rrafshi.scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dardan.rrafshi.commons.Randomizer;
import com.dardan.rrafshi.scanner.model.Banner;


public final class IotScanner
{
	public Map<String, List<Banner>> crawlIPv4(final List<Integer> ports)
	{
		final Map<String, List<Banner>> devices = Collections.synchronizedMap(new HashMap<>());
		final List<String> scannedHosts = new ArrayList<>();

		boolean allHostsScanned = false;

		while(!allHostsScanned) {
			final String host = ScanUtils.generateIPv4();

			if(scannedHosts.size() == Constants.NUMBER_OF_IP_V4_HOSTS)
				allHostsScanned = true;

			if(scannedHosts.contains(host))
				continue;

			final List<Banner> banners = this.grabBanner(host, ports);
			devices.put(host, banners);

			scannedHosts.add(host);
		}

		return devices;
	}

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

	public List<Banner> grabBanner(final String host, final List<Integer> ports)
	{
		final List<Banner> banners = Collections.synchronizedList(new ArrayList<>());
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
