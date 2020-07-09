package com.dardan.rrafshi.scanner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dardan.rrafshi.commons.Charsets;
import com.dardan.rrafshi.commons.Strings;
import com.dardan.rrafshi.scanner.model.Banner;


public final class BannerGrabber implements Runnable
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PortScanner.class);

	private final List<Banner> banners;
	private final String host;
	private final int port;


	public BannerGrabber(final List<Banner> banners, final String host, final int port)
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

			final String content = new String(message, Charsets.UTF_8);

			if(Strings.isNotBlank(content)) {
				LOGGER.info("Banner grabbed on " + this.host + ":" + this.port);
				final Banner banner = new Banner(this.host, this.port, content);
				this.banners.add(banner);
			}

		} catch (final IOException exception) {

		}
	}
}
