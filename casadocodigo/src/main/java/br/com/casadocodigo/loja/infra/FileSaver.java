package br.com.casadocodigo.loja.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileSaver {
	
	public static final String SERVER_PATH = "/home";
	
	public String write(Part arquivo, String relativePath) {
		try {
			relativePath += "/" + arquivo.getSubmittedFileName();
			arquivo.write(SERVER_PATH+"/"+relativePath);
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void transfer(Path source, OutputStream outputStream) {
		try {
			FileInputStream input = new FileInputStream(source.toFile());
			try (ReadableByteChannel inputChannel = Channels.newChannel(input);
					WritableByteChannel writableByteChannel = Channels.newChannel(outputStream)){
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024*10);
				while(inputChannel.read(buffer)!= -1) {
					buffer.flip();
					writableByteChannel.write(buffer);
					buffer.clear();
				}
			}catch(IOException e) {
				throw new RuntimeException(e);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
