package com.dardan.rrafshi.scanner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dardan.rrafshi.commons.Charsets;
import com.dardan.rrafshi.commons.Strings;


public final class BannerGrabber implements Runnable
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PortScanner.class);

	private final Map<Integer, String> banners;
	private final String host;
	private final int port;


	public BannerGrabber(final Map<Integer, String> banners, final String host, final int port)
	{
		this.banners = banners;
		this.host = host;
		this.port = port;
	}


	@Override
	public void run()
	{
		try(final Socket socket = new Socket(this.host, this.port)) {
			final InputStream input = new BufferedInputStream(socket.getInputStream());

			final byte[] message = new byte[input.available()];
			input.read(message);

			final String banner = new String(message, Charsets.UTF_8);

			if(Strings.isNotBlank(banner)) {
				LOGGER.info("Banner grabbed on " + this.host + ":" + this.port);
				this.banners.put(this.port, banner);
			}

		} catch (final IOException exception) {

		}
	}
}
