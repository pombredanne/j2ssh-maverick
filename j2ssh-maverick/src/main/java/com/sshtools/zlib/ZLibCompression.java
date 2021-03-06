/**
 * Copyright 2003-2014 SSHTOOLS Limited. All Rights Reserved.
 *
 * For product documentation visit https://www.sshtools.com/
 *
 * This file is part of J2SSH Maverick.
 *
 * J2SSH Maverick is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * J2SSH Maverick is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with J2SSH Maverick.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sshtools.zlib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZStream;
import com.sshtools.ssh.compression.SshCompression;

@SuppressWarnings("deprecation")
public class ZLibCompression implements SshCompression {

	public ZLibCompression() {
		stream = new ZStream();
	}

	public String getAlgorithm() {
		return "zlib";
	}

	static private final int BUF_SIZE = 65535;

	ByteArrayOutputStream compressOut = new ByteArrayOutputStream(BUF_SIZE);
	ByteArrayOutputStream uncompressOut = new ByteArrayOutputStream(BUF_SIZE);

	// private int type;
	private ZStream stream;

	private byte[] inflated_buf = new byte[BUF_SIZE];
	private byte[] tmpbuf = new byte[BUF_SIZE];

	public void init(int type, int level) {
		if (type == SshCompression.DEFLATER) {
			stream.deflateInit(level);
		} else if (type == SshCompression.INFLATER) {
			stream.inflateInit();
		}
	}

	public byte[] compress(byte[] buf, int start, int len) throws IOException {

		compressOut.reset();
		stream.next_in = buf;
		stream.next_in_index = start;
		stream.avail_in = len - start;
		int status;

		do {
			stream.next_out = tmpbuf;
			stream.next_out_index = 0;
			stream.avail_out = BUF_SIZE;
			status = stream.deflate(JZlib.Z_PARTIAL_FLUSH);
			switch (status) {
			case JZlib.Z_OK:
				compressOut.write(tmpbuf, 0, BUF_SIZE - stream.avail_out);
				break;
			default:
				throw new IOException("compress: deflate returnd " + status);
			}
		} while (stream.avail_out == 0);

		return compressOut.toByteArray();
	}

	public byte[] uncompress(byte[] buffer, int start, int length)
			throws IOException {

		// int inflated_end = 0;
		uncompressOut.reset();

		stream.next_in = buffer;
		stream.next_in_index = start;
		stream.avail_in = length;

		while (true) {
			stream.next_out = inflated_buf;
			stream.next_out_index = 0;
			stream.avail_out = BUF_SIZE;
			int status = stream.inflate(JZlib.Z_PARTIAL_FLUSH);
			switch (status) {
			case JZlib.Z_OK:
				uncompressOut.write(inflated_buf, 0, BUF_SIZE
						- stream.avail_out);
				break;
			case JZlib.Z_BUF_ERROR:
				return uncompressOut.toByteArray();
			default:
				throw new IOException("uncompress: inflate returnd " + status);
			}
		}
	}

}
