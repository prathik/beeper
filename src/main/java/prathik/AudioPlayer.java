package prathik;

import io.vavr.control.Try;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
  private final byte[] clipContent;

  public AudioPlayer(Path clipFile) {
    this.clipContent = Try.of(() -> Files.readAllBytes(clipFile)).get();
  }

  public void play() {
    AudioListener listener = new AudioListener();
    try (AudioInputStream audioInputStream =
            AudioSystem.getAudioInputStream(new ByteArrayInputStream(clipContent));
        Clip clip = AudioSystem.getClip()) {
      clip.addLineListener(listener);
      clip.open(audioInputStream);
      clip.start();
      listener.waitUntilDone();
    } catch (InterruptedException
        | LineUnavailableException
        | IOException
        | UnsupportedAudioFileException e) {
      // Ignore
    }
  }
}
