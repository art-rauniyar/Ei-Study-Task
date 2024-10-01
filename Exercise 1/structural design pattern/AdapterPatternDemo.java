// AdapterPatternDemo.java

// Target Interface
interface MediaPlayer {
    void play(String fileName);
}

// Concrete Target: MP3 Player
class Mp3Player implements MediaPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

// Adaptee: MP4 Player
class Mp4Player {
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

// Adapter Class
class MediaAdapter implements MediaPlayer {
    private Mp4Player mp4Player;
    
    public MediaAdapter() {
        mp4Player = new Mp4Player();
    }
    
    @Override
    public void play(String fileName) {
        mp4Player.playMp4(fileName);
    }
}

// Client
public class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer mp3Player = new Mp3Player();
        mp3Player.play("song.mp3");
        
        MediaPlayer mp4Player = new MediaAdapter();
        mp4Player.play("movie.mp4");
    }
}
