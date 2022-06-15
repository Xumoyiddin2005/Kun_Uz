package com.company.exps;

public class ItemNotFoundEseption extends RuntimeException{
    public ItemNotFoundEseption(String massage) {
        super(massage);
    }
}
