package main;

import javazoom.jl.player.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread {
	private Player musicPlayer;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../Musics/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			musicPlayer = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if(musicPlayer == null)
			return 0;
		return musicPlayer.getPosition();
	}

	public void close() {
		isLoop = false;
		musicPlayer.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			musicPlayer.play();
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			musicPlayer = new Player(bis);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
