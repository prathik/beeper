package prathik;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    AudioPlayer audioPlayer = new AudioPlayer(Paths.get("src/main/resources/output.wav"));
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    var future = executorService.scheduleWithFixedDelay(audioPlayer::play, 0, 3, TimeUnit.SECONDS);

    Thread.sleep(Duration.ofMinutes(5).toSeconds());
    future.cancel(true);
    executorService.shutdownNow();
    executorService.awaitTermination(1, TimeUnit.SECONDS);
  }
}
