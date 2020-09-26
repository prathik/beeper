package etu;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AudioPlayer audioPlayer = new AudioPlayer(Paths.get("src/main/resources/output.wav"));
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        var future = executorService.scheduleWithFixedDelay(audioPlayer::play, 0, Integer.parseInt(args[1]), TimeUnit.SECONDS);

        Thread.sleep(Duration.ofMinutes(Integer.parseInt(args[0])).toMillis());
        future.cancel(true);
        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}
