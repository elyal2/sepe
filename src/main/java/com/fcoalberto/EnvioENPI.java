package com.fcoalberto;

import java.io.Serializable;

public class EnvioENPI implements Serializable {
    public static final String ENVIOCOD = "CODIGO_AGENCIA";
    public static final String ANOMES = "AÑO_MES_ENVIO";
    private boolean anual = false;
    private String agencia;
    private String fecha;

    public EnvioENPI() {
    }

    public String getAgencia() {
        return this.agencia;
    }

    public void setAgencia(String agencia) throws Exception {
        if (agencia != null && !agencia.equals("") && agencia.matches("\\d{10}")) {
            this.agencia = agencia;
        } else {
            throw new Exception("AGENCIA [" + agencia + "] valor no valido");
        }
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) throws Exception {
        if (fecha != null && fecha.matches("\\d{6}")) {
            this.fecha = fecha;
        } else {
            throw new Exception("FECHA [" + fecha + "] no es un valor válido");
        }
    }

    public boolean isAnual() {
        return this.anual;
    }

    public void setAnual(boolean anual) {
        this.anual = anual;
    }
}
