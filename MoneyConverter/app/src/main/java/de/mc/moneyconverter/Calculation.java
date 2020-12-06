package de.mc.moneyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class Calculation {


    public BigDecimal getResult(String quellwaehrung, String zielwaehrung, BigDecimal amount, LinkedHashMap<String, String> wechselkurse, Integer nachkommastellen){
        BigDecimal wechselkursQuellwaehrung = new BigDecimal(wechselkurse.get(quellwaehrung));
        BigDecimal wechselkursZielwaehrungFlipped = new BigDecimal(wechselkurse.get(zielwaehrung));
        BigDecimal wechselkursZielwaehrung = BigDecimal.ONE.divide(wechselkursZielwaehrungFlipped, wechselkursZielwaehrungFlipped.scale(), RoundingMode.DOWN);
        BigDecimal amountinEuro = wechselkursQuellwaehrung.multiply(new BigDecimal(amount.toString()));
        BigDecimal result = amountinEuro.multiply(wechselkursZielwaehrung);
        result = result.setScale(nachkommastellen, BigDecimal.ROUND_HALF_EVEN);
        return result;
    }

}
