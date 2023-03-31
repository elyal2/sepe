package com.fcoalberto;

import java.io.Serializable;

public class DatosAgregados implements Serializable {
    public static final String DATOS_AGREGADOS = "DATOS_AGREGADOS";
    public static final String TOTAL_PERSONAS = "TOTAL_PERSONAS";
    public static final String TOTAL_NUEVAS_REGISTRADAS = "TOTAL_NUEVAS_REGISTRADAS";
    public static final String TOTAL_PERSONAS_PERCEPTORES = "TOTAL_PERSONAS_PERCEPTORES";
    public static final String TOTAL_PERSONAS_INSERCION = "TOTAL_PERSONAS_INSERCION";
    public static final String TOTAL_OFERTAS = "TOTAL_OFERTAS";
    public static final String TOTAL_OFERTAS_ENVIADAS = "TOTAL_OFERTAS_ENVIADAS";
    public static final String TOTAL_OFERTAS_CUBIERTAS = "TOTAL_OFERTAS_CUBIERTAS";
    public static final String TOTAL_PUESTOS = "TOTAL_PUESTOS";
    public static final String TOTAL_PUESTOS_CUBIERTOS = "TOTAL_PUESTOS_CUBIERTOS";
    public static final String TOTAL_CONTRATOS = "TOTAL_CONTRATOS";
    public static final String TOTAL_CONTRATOS_INDEFINIDOS = "TOTAL_CONTRATOS_INDEFINIDOS";
    public static final String TOTAL_PERSONAS_COLOCADAS = "TOTAL_PERSONAS_COLOCADAS";
    private String totalPersonas;
    private String totalNuevas;
    private String totalPerceptores;
    private String totalInsercion;
    private String totalOfertas;
    private String totalEnviadas;
    private String totalCubiertas;
    private String totalPuestos;
    private String totalCubiertos;
    private String totalContratos;
    private String totalIndefinidos;
    private String totalColocadas;

    public DatosAgregados() {
    }

    public String getTotalPersonas() {
        return this.totalPersonas;
    }

    public void setTotalPersonas(String totalPersonas) {
        this.totalPersonas = totalPersonas;
    }

    public String getTotalNuevas() {
        return this.totalNuevas;
    }

    public void setTotalNuevas(String totalNuevas) {
        this.totalNuevas = totalNuevas;
    }

    public String getTotalPerceptores() {
        return this.totalPerceptores;
    }

    public void setTotalPerceptores(String totalPerceptores) {
        this.totalPerceptores = totalPerceptores;
    }

    public String getTotalInsercion() {
        return this.totalInsercion;
    }

    public void setTotalInsercion(String totalInsercion) {
        this.totalInsercion = totalInsercion;
    }

    public String getTotalOfertas() {
        return this.totalOfertas;
    }

    public void setTotalOfertas(String totalOfertas) {
        this.totalOfertas = totalOfertas;
    }

    public String getTotalEnviadas() {
        return this.totalEnviadas;
    }

    public void setTotalEnviadas(String totalEnviadas) {
        this.totalEnviadas = totalEnviadas;
    }

    public String getTotalCubiertas() {
        return this.totalCubiertas;
    }

    public void setTotalCubiertas(String totalCubiertas) {
        this.totalCubiertas = totalCubiertas;
    }

    public String getTotalPuestos() {
        return this.totalPuestos;
    }

    public void setTotalPuestos(String totalPuestos) {
        this.totalPuestos = totalPuestos;
    }

    public String getTotalCubiertos() {
        return this.totalCubiertos;
    }

    public void setTotalCubiertos(String totalCubiertos) {
        this.totalCubiertos = totalCubiertos;
    }

    public String getTotalContratos() {
        return this.totalContratos;
    }

    public void setTotalContratos(String totalContratos) {
        this.totalContratos = totalContratos;
    }

    public String getTotalIndefinidos() {
        return this.totalIndefinidos;
    }

    public void setTotalIndefinidos(String totalIndefinidos) {
        this.totalIndefinidos = totalIndefinidos;
    }

    public String getTotalColocadas() {
        return this.totalColocadas;
    }

    public void setTotalColocadas(String totalColocadas) {
        this.totalColocadas = totalColocadas;
    }
}
