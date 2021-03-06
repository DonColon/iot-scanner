package com.dardan.rrafshi.scanner.examples;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dardan.rrafshi.commons.management.Threads;
import com.dardan.rrafshi.scanner.IotScanner;
import com.dardan.rrafshi.scanner.PortScanner;


public final class PortScanning
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PortScanner.class);


	public static void main(final String[] args)
	{
		final List<Integer> ports = Arrays.asList(80, 443, 8080, 3306, 14141, 21, 22, 5500);
		final String host = "127.0.0.1";

		final LocalDateTime start = LocalDateTime.now();
		LOGGER.info("Port scanning started at " + start);

		final IotScanner iotScanner = new IotScanner();
		final List<Integer> openPorts = iotScanner.scanPorts(host, ports);

		Threads.safeSleep(1, TimeUnit.SECONDS);

		LOGGER.info("Open Ports at " + host + " -> " + openPorts);

		final LocalDateTime end = LocalDateTime.now();
		LOGGER.info("Port scanning finished at " + LocalDateTime.now());

		final long duration = start.until(end, ChronoUnit.MINUTES);
		LOGGER.info("Port scanning lasted " + duration + " min");
	}
}
