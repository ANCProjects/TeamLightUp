package com.zaksontech.www.langtutor;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class GreetingsActivity extends AppCompatActivity {

    //playback of all the sound files
    private MediaPlayer mMediaPlayer;

    //audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus is
     * gained or lost
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                //Pause playback and reset player to the start of the file. That way, we can
                //play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // if AUDIOFOCUS_GAIN is true, resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //else Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    //this get triggered when mediaplayer has finished playing
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //creates a list of words
        final ArrayList<Word> words = new ArrayList<Word>();

        //words.add("one");
        words.add(new Word("Where are you going?", "Nibo lo nlo?", R.raw.audio_palceholder));
        words.add(new Word("What is your name?", "Kini oruko re?", R.raw.audio_palceholder));
        words.add(new Word("My name is...", "Oruko mi ni...", R.raw.audio_palceholder));
        words.add(new Word("How are you?", "Bawo ni?", R.raw.audio_palceholder));
        words.add(new Word("I'm fine", "Mowa daada", R.raw.audio_palceholder));
        words.add(new Word("Are you coming?", "Se o nbo?", R.raw.audio_palceholder));
        words.add(new Word("Yes, I'm coming", "Beeni, mo nbo", R.raw.audio_palceholder));
        words.add(new Word("Have you eaten?", "Se oti jeun?", R.raw.audio_palceholder));
        words.add(new Word("Yes, I've eaten", "Beeni, moti jeun", R.raw.audio_palceholder));
        words.add(new Word("Let's go", "Je kalo", R.raw.audio_palceholder));
        words.add(new Word("Come here", "wa nbi", R.raw.audio_palceholder));
        words.add(new Word("Stand up", "Dide", R.raw.audio_palceholder));
        words.add(new Word("Sit down", "Joko", R.raw.audio_palceholder));

//create an instance of Langtutor adaper whose data source is a list from Word
        LangtutorAdapter a = new LangtutorAdapter(this, words, R.color.greetingsColor);

        //find the listview with id list and set it to the adpter above
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(a);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the Word object at the given position the user clicked on
                Word word = words.get(position);

                //Request audio focus for a short amount of time with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);



                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup MediaPlayer for the audio resource associated with the current word
                    mMediaPlayer = MediaPlayer.create(GreetingsActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources
        releaseMediaPlayer();
    }

    //The releaseMediaPlayer method which cleans up the media player by releasing its resources.
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            //noinspection deprecation
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}
