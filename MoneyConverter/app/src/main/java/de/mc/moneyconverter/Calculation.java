package de.mc.moneyconverter;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class Calculation {
    private String quellwaehrung;
    private String zielwaehrung;
    private BigDecimal amount;
    private  BigDecimal result;
    private LinkedHashMap<String, String> wechselkurse;

    public Calculation(String quellwaehrung, String zielwaehrung, BigDecimal amount, LinkedHashMap<String, String> wechselkurse){
        this.quellwaehrung=quellwaehrung;
        this.zielwaehrung=zielwaehrung;
        this.amount=amount;
        this.wechselkurse=wechselkurse;
    }

    public BigDecimal getResult(){
        BigDecimal wechselkursQuellwaehrung = new BigDecimal(this.wechselkurse.get(this.quellwaehrung));
        BigDecimal wechselkursZielwaehrungFlipped = new BigDecimal(this.wechselkurse.get(this.zielwaehrung));
        BigDecimal wechselkursZielwaehrung = BigDecimal.ONE.divide(wechselkursZielwaehrungFlipped, wechselkursZielwaehrungFlipped.scale(), RoundingMode.DOWN);
        BigDecimal amountinEuro = wechselkursQuellwaehrung.multiply(new BigDecimal(this.amount.toString()));
        BigDecimal result = amountinEuro.multiply(wechselkursZielwaehrung);
        this.result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return this.result;
    }

}
