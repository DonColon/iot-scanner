package com.dardan.rrafshi.scanner;

import com.dardan.rrafshi.commons.Randomizer;


public final class ScanUtils
{
	private ScanUtils() {}


	public static String generateIPv4()
	{
		String ip = "";

		for(int i = 0; i < Constants.NUMBER_OF_OCTETS; i++) {
			final int octet = Randomizer.generateInteger(Constants.IP_V4_RANGE);
			ip += octet + ".";
		}

		return ip.substring(0, ip.length() - 1);
	}
}
