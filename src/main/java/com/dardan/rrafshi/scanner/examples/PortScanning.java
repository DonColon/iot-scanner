package com.dardan.rrafshi.scanner.examples;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dardan.rrafshi.scanner.IotScanner;
import com.dardan.rrafshi.scanner.PortScanner;


public final class PortScanning
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PortScanner.class);


	public static void main(final String[] args)
	{
		final LocalDateTime start = LocalDateTime.now();
		LOGGER.info("Port scanning started at " + start);

		final IotScanner iotScanner = new IotScanner();
		final List<Integer> openPorts = iotScanner.scanPorts("127.0.0.1");
		System.out.println(openPorts);

		final LocalDateTime end = LocalDateTime.now();
		LOGGER.info("Port scanning finished at " + LocalDateTime.now());

		final long duration = start.until(end, ChronoUnit.MINUTES);
		LOGGER.info("Port scanning lasted " + duration + " min");
	}
}
