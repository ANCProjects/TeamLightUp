package com.zaksontech.www.langtutor;

/**
 * Created by Zaks0n on 7/23/2017.
 */

public class Word {

    //declarations for the contents of the word object
    private String mEnglishTranslation;
    private String mYorubaTranslation;
    private int mAudioResourceId;
    private int mImageResourceId = NO_IMAGE;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE = -1;


    //constructors for contents of the word object
    public Word(String englishTranslation, String yorubaTranslation, int audioResourceId){
        mEnglishTranslation = englishTranslation;
        mYorubaTranslation = yorubaTranslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String englishTranslation, String yorubaTranslation, int imageResourceId,
                int audioResourceId) {
        mEnglishTranslation = englishTranslation;
        mYorubaTranslation = yorubaTranslation;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;

    }

    //getter methods for the constructors above
    public String getEnglishTranslation(){
        return mEnglishTranslation;
    }

    public String getYorubaTranslation(){
        return mYorubaTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    //Returns whether or not there is an image for this word.
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE;
    }
}
