package com.dardan.rrafshi.scanner.model;

public final class Banner
{
	private final String host;
	private final int port;
	private final String content;


	public Banner(final String host, final int port, final String content)
	{
		this.host = host;
		this.port = port;
		this.content = content;
	}


	public String getHost()
	{
		return this.host;
	}

	public int getPort()
	{
		return this.port;
	}

	public String getContent()
	{
		return this.content;
	}
}
