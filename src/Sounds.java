import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds implements Runnable {

	AudioInputStream audio;
	static Clip dogeSong, sandstorm, wow, save;

	public static void run(String path) {
		// play sound file

		// plays doge song
		if (path.equals("dogeSong")) {
			if (dogeSong.isRunning() == true) {
				closeAll();
			} else {
				closeAll();
				dogeSong.start();
				dogeSong.loop(100);
			}
		}
		// plays Sandstorm by Darude
		if (path.equals("sandstorm")) {
			if (sandstorm.isRunning() == true) {
				closeAll();
			} else {
				closeAll();
				sandstorm.start();
				sandstorm.loop(100);
			}
		}
		// plays wow.wav
		if (path.equals("wow")) {

			if (wow.isRunning() == true) {
			} else {
				wow.setFramePosition(0);
				wow.start();
			}

		}
		// plays save.wav
		if (path.equals("save")) {

			if (save.isRunning() == true) {
			} else {
				save.setFramePosition(0);
				save.start();
			}

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public static void initialize() {
		// Finding sound file

		try {
			// initializes all audio clips
			dogeSong = AudioSystem.getClip();
			sandstorm = AudioSystem.getClip();
			wow = AudioSystem.getClip();
			save = AudioSystem.getClip();

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			// opens clips
			dogeSong.open(AudioSystem.getAudioInputStream(new File(
					"Audio//doge.wav")));
			sandstorm.open(AudioSystem.getAudioInputStream(new File(
					"Audio//sandstorm.wav")));
			wow.open(AudioSystem
					.getAudioInputStream(new File("Audio//wow1.wav")));
			// reduce volume of wow and song
			FloatControl gainControl = (FloatControl) wow
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);

			FloatControl gainControl2 = (FloatControl) dogeSong
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl2.setValue(-5.0f);

			save.open(AudioSystem.getAudioInputStream(new File(
					"Audio//save.wav")));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// make sure its .wav
			e.printStackTrace();
		}

	}

	public static void closeAll() {
		// stop all clips
		dogeSong.stop();
		sandstorm.stop();
		wow.stop();
		save.stop();
	}

	public static int increase() {

		// increase volume
		FloatControl gainControl = (FloatControl) dogeSong
				.getControl(FloatControl.Type.MASTER_GAIN);

		if (gainControl.getValue() + 5 <= 6) {
			gainControl.setValue(gainControl.getValue() + 5.0f);
			return 1;
		}

		return 0;

	}

	public static int decrease() {
		// decrease volume
		FloatControl gainControl = (FloatControl) dogeSong
				.getControl(FloatControl.Type.MASTER_GAIN);

		if (gainControl.getValue() - 5 >= -20) {
			gainControl.setValue(gainControl.getValue() - 5.0f);
			return -1;
		}
		return 0;
	}

}
