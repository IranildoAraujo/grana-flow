package com.granaflow.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.granaflow.exception.DataInvalidaException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormatDateUtil {

	/**
     * 
     * Retorna data em formato DD/MM/YYYY HH:mm:ss 
     * 
     * @param Date data
     *          Data que será formatada.
     * 
     * @return String (DD/MM/YYYY HH:mm:ss)
     */
    public static String getStringDataHora(Date data) {
        if (data == null) {
            throw new DataInvalidaException("Data inválida.");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }
}
