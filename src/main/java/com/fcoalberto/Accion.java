package com.fcoalberto;

import java.io.Serializable;
import java.util.Locale;

public class Accion implements Serializable {
    public static final String ACCION = "ACCION";
    public static final String ID_TRABAJADOR = "ID_TRABAJADOR";
    public static final String NOMBRE_TRABAJADOR = "NOMBRE_TRABAJADOR";
    public static final int MAX_NOMBRE = 15;
    public static final String APELLIDO1_TRABAJADOR = "APELLIDO1_TRABAJADOR";
    public static final String APELLIDO2_TRABAJADOR = "APELLIDO2_TRABAJADOR";
    public static final int MAX_APPELLIDO = 20;
    public static final String FECHA_NACIMIENTO = "FECHA_NACIMIENTO";
    public static final String SEXO_TRABAJADOR = "SEXO_TRABAJADOR";
    public static final String NIVEL_FORMATIVO = "NIVEL_FORMATIVO";
    public static final String DISCAPACIDAD = "DISCAPACIDAD";
    public static final String INMIGRANTE = "INMIGRANTE";
    public static final String COLOCACION = "COLOCACION";
    public static final String FECHA_COLOCACION = "FECHA_COLOCACION";
    public static final String TIPO_CONTRATO = "TIPO_CONTRATO";
    public static final String CIF_NIF_EMPRESA = "CIF_NIF_EMPRESA";
    public static final String RAZON_SOCIAL_EMPRESA = "RAZON_SOCIAL_EMPRESA";
    public static final int MAX_RAZON = 55;
    private String idtrabajador;
    private String nombretrabajador;
    private String apellido1;
    private String apellido2;
    private String fechanacimiento;
    private String sexo;
    private String nivelformativo;
    private String discapacidad;
    private String inmigrante;
    private String colocacion;
    private String fechacolocacion;
    private String tipocontrato;
    private String cifempresa;
    private String razonsocial;

    public Accion() {
    }

    private String recortar(String dato, int longitud) {
        return dato.substring(0, Math.min(dato.length(), longitud));
    }

    public String getIdtrabajador() {
        return this.idtrabajador;
    }

    public void setIdtrabajador(String id) throws Exception {
        this.idtrabajador = id;
    }

    public String getNombretrabajador() {
        return this.nombretrabajador;
    }

    public void setNombretrabajador(String nombre) throws Exception {
        if (nombre != null && !nombre.equals("")) {
            this.nombretrabajador = this.recortar(nombre, 15);
        } else {
            throw new Exception("Nombre trabajador no puede estar vacio");
        }
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String ape) throws Exception {
        if (ape != null && !ape.equals("")) {
            this.apellido1 = this.recortar(ape, 20);
        } else {
            throw new Exception("Apellido trabajador no puede estar vacio");
        }
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String ape) {
        if (ape != null) {
            this.apellido2 = this.recortar(ape, 20);
        }

    }

    public String getFechanacimiento() {
        return this.fechanacimiento;
    }

    public void setFechanacimiento(String fecha) throws Exception {
        if (fecha != null && fecha.matches("\\d{8}")) {
            this.fechanacimiento = fecha;
        } else {
            throw new Exception("FECHA NACIMIENTO[" + fecha + "] no es un valor válido");
        }
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) throws Exception {
        if (sexo.equals("Varón")) {
            sexo = "1";
        }

        if (sexo.equals("Mujer")) {
            sexo = "1";
        }

        switch (sexo) {
            case "1":
            case "2":
                this.sexo = sexo;
                return;
            default:
                throw new Exception("SEXO[" + sexo + "] no es un valor válido");
        }
    }

    public String getNivelformativo() {
        return this.nivelformativo;
    }

    public void setNivelformativo(String nivelformativo) throws Exception {
        if (nivelformativo.equals("Sin estudios")) {
            nivelformativo = "00";
        }

        if (nivelformativo.equals("Estudios Primarios")) {
            nivelformativo = "10";
        }

        if (nivelformativo.equals("Estudios Secundarios")) {
            nivelformativo = "20";
        }

        if (nivelformativo.equals("Estudios Postsecundarios")) {
            nivelformativo = "30";
        }

        switch (nivelformativo) {
            case "0":
                this.nivelformativo = "00";
                break;
            case "00":
            case "10":
            case "20":
            case "30":
                this.nivelformativo = nivelformativo;
                break;
            default:
                throw new Exception("NIVEL FORMATIVO[" + nivelformativo + "] no es un valor válido");
        }

    }

    public String getDiscapacidad() {
        return this.discapacidad;
    }

    public void setDiscapacidad(String discapacidad) throws Exception {
        if (discapacidad.equals("Si")) {
            discapacidad = "S";
        }

        if (discapacidad.equals("No")) {
            discapacidad = "N";
        }

        switch (discapacidad.toUpperCase(Locale.ROOT)) {
            case "S":
            case "N":
                this.discapacidad = discapacidad;
                return;
            default:
                throw new Exception("DISCAPACIDAD[" + discapacidad + "] no es un valor válido");
        }
    }

    public String getInmigrante() {
        return this.inmigrante;
    }

    public void setInmigrante(String inmigrante) throws Exception {
        if (inmigrante.equals("Si")) {
            inmigrante = "S";
        }

        if (inmigrante.equals("No")) {
            inmigrante = "N";
        }

        switch (inmigrante.toUpperCase(Locale.ROOT)) {
            case "S":
            case "N":
                this.inmigrante = inmigrante;
                return;
            default:
                throw new Exception("INMIGRANTE[" + inmigrante + "] no es un valor válido");
        }
    }

    public String getColocacion() {
        return this.colocacion;
    }

    public void setColocacion(String colocacion) throws Exception {
        if (colocacion.equals("Si")) {
            colocacion = "S";
        }

        if (colocacion.equals("No")) {
            colocacion = "N";
        }

        switch (colocacion.toUpperCase(Locale.ROOT)) {
            case "S":
            case "N":
                this.colocacion = colocacion;
                return;
            default:
                throw new Exception("COLOCACION[" + colocacion + "] no es un valor válido");
        }
    }

    public String getFechacolocacion() {
        return this.fechacolocacion;
    }

    public void setFechacolocacion(String fechacolocacion) throws Exception {
        if (fechacolocacion != null && fechacolocacion.matches("\\d{8}")) {
            this.fechacolocacion = fechacolocacion;
        } else {
            throw new Exception("FECHA COLOCACION[" + fechacolocacion + "] no es un valor válido");
        }
    }

    public String getTipocontrato() {
        return this.tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) throws Exception {
        switch (tipocontrato) {
            case "1":
            case "3":
                this.tipocontrato = "00" + tipocontrato;
                break;
            case "001":
            case "003":
            case "401":
            case "501":
                this.tipocontrato = tipocontrato;
                break;
            default:
                throw new Exception("TIPO CONTRATO[" + tipocontrato + "] no es un valor válido");
        }

        this.tipocontrato = tipocontrato;
    }

    public String getCifempresa() {
        return this.cifempresa;
    }

    public void setCifempresa(String cifempresa) {
        this.cifempresa = cifempresa;
    }

    public String getRazonsocial() {
        return this.razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        if (razonsocial != null) {
            this.razonsocial = this.recortar(razonsocial, 55);
        }

    }
}
