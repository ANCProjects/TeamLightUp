package com.zaksontech.www.langtutor;

/**
 * Created by Zaks0n on 7/23/2017.
 */

public class Word {

    private String mEnglishTranslation;
    private String mYorubaTranslation;



    public Word(String englishTranslation, String yorubaTranslation){
        mEnglishTranslation = englishTranslation;
        mYorubaTranslation = yorubaTranslation;
    }

    public String getEnglishTranslation(){
        return mEnglishTranslation;
    }

    public String getYorubaTranslation(){
        return mYorubaTranslation;
    }
}
