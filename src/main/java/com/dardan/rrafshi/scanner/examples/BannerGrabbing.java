package com.dardan.rrafshi.scanner.examples;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dardan.rrafshi.commons.management.Threads;
import com.dardan.rrafshi.scanner.BannerGrabber;
import com.dardan.rrafshi.scanner.IotScanner;


public final class BannerGrabbing
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BannerGrabber.class);


	public static void main(final String[] args)
	{
		final List<Integer> openPorts = Arrays.asList(3306, 14141, 21);
		final String host = "127.0.0.1";

		final LocalDateTime start = LocalDateTime.now();
		LOGGER.info("Port scanning started at " + start);

		final IotScanner iotScanner = new IotScanner();
		final Map<Integer, String> banners = iotScanner.grabBanner(host, openPorts);

		Threads.safeSleep(5, TimeUnit.SECONDS);

		System.out.println("Banners at " + host + ":");
		System.out.println("================================================================================");
		for(final Entry<Integer, String> entry : banners.entrySet()) {
			System.out.println("Port: " + entry.getKey());
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(entry.getValue() + "\n");
		}

		final LocalDateTime end = LocalDateTime.now();
		LOGGER.info("Port scanning finished at " + LocalDateTime.now());

		final long duration = start.until(end, ChronoUnit.MINUTES);
		LOGGER.info("Port scanning lasted " + duration + " min");
	}
}
