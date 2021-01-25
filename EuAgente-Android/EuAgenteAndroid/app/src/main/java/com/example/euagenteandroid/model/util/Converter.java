package com.example.euagenteandroid.model.util;

import java.util.Calendar;
import java.util.Date;

public class Converter {
    private Converter() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getDate(String data) {
        String[] dataSplit = data.split("/");
        int dia = Integer.parseInt(dataSplit[0]);
        int mes = Integer.parseInt(dataSplit[1]) - 1;
        int ano = Integer.parseInt(dataSplit[2]);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }
}
