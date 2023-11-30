//package main;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import java.io.IOException;
//import java.net.URL;
//
//public class Sound {
//
//    Clip clip;
//    URL soundURL[] = new URL[30];
//
//    public Sound() {
//        soundURL[0] = getClass().getResource("../Musics/home_bgm.wav");
//        soundURL[1] = getClass().getResource("../Musics/base_bgm.mp3");
//        soundURL[2] = getClass().getResource("../Musics/catch_bgm.mp3");
//        soundURL[3] = getClass().getResource("../Musics/slot_bgm.mp3");
//        soundURL[4] = getClass().getResource("../Musics/graduation.mp3");
//        
//    }
//
//    public void setFile(int i) {
//        try {
//            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
//            clip = AudioSystem.getClip();
//            clip.open(ais);
//        } catch (Exception e) {
//        }
//    }
//    public void play(){
//        clip.start();
//
//    }
//    public void loop() {
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//    }
//    public void stop() {
//        clip.stop();
//    }
//}
