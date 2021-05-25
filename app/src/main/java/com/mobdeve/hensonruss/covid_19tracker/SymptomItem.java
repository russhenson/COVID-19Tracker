package com.mobdeve.hensonruss.covid_19tracker;

public class SymptomItem {
    public enum ItemType{
        typeOne, typeTwo;
    }
    private String content;
    private ItemType type;

    public SymptomItem(String content, ItemType type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
