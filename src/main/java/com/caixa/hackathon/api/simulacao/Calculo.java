package com.caixa.hackathon.api.simulacao;

import java.util.ArrayList;
import java.util.List;

public class Calculo {
    public static List<ParcelaDTO> calculaSac(Double valorDesejado, Integer prazo, Double taxa){
        List<ParcelaDTO> simula = new ArrayList<>();
        double saldoDevedorInicial = valorDesejado;
        double amortizacao = valorDesejado / prazo;

        for (int i = 0; i < prazo; i++) {
            double juros = saldoDevedorInicial * taxa;
            double parcela = amortizacao + juros;
            double saldoDevedorFinal = saldoDevedorInicial - amortizacao;

            simula.add(new ParcelaDTO((i+1), amortizacao, juros, parcela));
            saldoDevedorInicial = saldoDevedorFinal;
        }

        return simula;
    }

    public static List<ParcelaDTO> calculaPrice(Double valorDesejado, Integer prazo, Double taxa){
        List<ParcelaDTO> simula = new ArrayList<>();
        double saldoDevedorInicial = valorDesejado;
        double parcela = (valorDesejado * taxa) / (1 - Math.pow(1 + taxa, -prazo));

        for (int i = 0; i < prazo; i++) {
            double juros = saldoDevedorInicial * taxa;
            double amortizacao = parcela - juros;
            double saldoDevedorFinal = saldoDevedorInicial - amortizacao;

            simula.add(new ParcelaDTO((i+1), amortizacao, juros, parcela));
            saldoDevedorInicial = saldoDevedorFinal;
        }

        return simula;
    }
}
