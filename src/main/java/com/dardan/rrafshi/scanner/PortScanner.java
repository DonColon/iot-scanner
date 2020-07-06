package com.dardan.rrafshi.scanner;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class PortScanner implements Runnable
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PortScanner.class);

	private final List<Integer> openPorts;
	private final String host;
	private final int port;


	public PortScanner(final List<Integer> openPorts, final String host, final int port)
	{
		this.openPorts = openPorts;
		this.host = host;
		this.port = port;
	}


	@Override
	public void run()
	{
		try(final Socket socket = new Socket(this.host, this.port)) {

			LOGGER.info("Service found at " + this.host + ":" + this.port);
			this.openPorts.add(this.port);

		} catch (final IOException exception) {

		}
	}
}
