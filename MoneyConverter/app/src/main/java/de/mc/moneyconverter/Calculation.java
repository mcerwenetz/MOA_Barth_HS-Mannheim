package de.mc.moneyconverter;

import java.math.BigDecimal;

public class Calculation {
    private String quellwaehrung;
    private String zielwaehrung;
    private BigDecimal amount;
    private  BigDecimal result;

    public Calculation(String quellwaehrung, String zielwaehrung, BigDecimal amount){
        this.quellwaehrung=quellwaehrung;
        this.zielwaehrung=zielwaehrung;
        this.amount=amount;
    }

    public BigDecimal getResult(){
        //Todo: Umrechnungskurse aus Ressourcen beziehen
        BigDecimal amountInEuro=null;
        //Todo: Strings aus Ressourcen beziehen
        if(quellwaehrung.equals("EUR")){
            amountInEuro=amount;
        }
        else if (quellwaehrung.equals("USD")){
            amountInEuro = amount.multiply(new BigDecimal("0.84"));
        }
        else if (quellwaehrung.equals("GRD")){
            amountInEuro = amount.multiply(new BigDecimal("0.002934"));
        }
        //Umrechnen in Zielwährung
        if (zielwaehrung.equals("EUR")){
            this.result = amountInEuro;
        }
        else if (zielwaehrung.equals("USD")){
            this.result = amountInEuro.multiply(new BigDecimal("1.17"));
        }
        else if (zielwaehrung.equals("GRD")){
            this.result = amountInEuro.multiply(new BigDecimal("340.75"));
        }
        //Todo: Bug: 1€ ist ein 1€ aber 1$ wird 0.98$
        result = result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return this.result;
    }

}
