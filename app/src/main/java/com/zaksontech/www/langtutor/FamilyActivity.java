package com.zaksontech.www.langtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

      /*  words.add(new Word("A", "A"));
        words.add(new Word("B", "B"));
        words.add(new Word("D", "D"));
        words.add(new Word("E", "A"));
        words.add(new Word("Ẹ", "E"));
        words.add(new Word("F", "FEE"));
        words.add(new Word("G", "G"));
        words.add(new Word("GB", "GB"));
        words.add(new Word("H", "HE"));
        words.add(new Word("I", "E"));
        words.add(new Word("J", "J"));
        words.add(new Word("K", "KE"));
        words.add(new Word("L", "LE"));
        words.add(new Word("M", "M"));
        words.add(new Word("N", "N"));
        words.add(new Word("O", "O"));
        words.add(new Word("Ọ", "OHO"));
        words.add(new Word("P", "J"));
        words.add(new Word("R", "J"));
        words.add(new Word("P", "KP"));
        words.add(new Word("S", "S"));
        words.add(new Word("Ṣ", "SH"));
        words.add(new Word("T", "J"));
        words.add(new Word("U", "U"));
        words.add(new Word("W", "WE"));
        words.add(new Word("Y", "YE"));*/

        WordAdapter adapter = new WordAdapter(this, words, R.color.familyColor);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}

