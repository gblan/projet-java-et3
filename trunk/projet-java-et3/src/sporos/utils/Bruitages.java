package sporos.utils;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Bruitages {

	private boolean active = true;
	private static Bruitages instance = null;

	public static Bruitages getInstance() {
		if (instance == null) {
			instance = new Bruitages();
		}
		return instance;
	}

	private AudioFormat audioFormat;
	private AudioInputStream audioInputStream;
	private SourceDataLine sourceDataLine;

	public void playSong(String fileName) {
		if (isActive()) {
			jouerSon(fileName);
		}
	}

	private void jouerSon(String fileName) {
		try {
			File soundFile = new File(fileName);
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioInputStream.getFormat();

			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat);

			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			new PlayThread().start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[10000];

		public void run() {
			try {
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				int cnt;

				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (cnt > 0) {

						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}

				sourceDataLine.drain();
				sourceDataLine.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
