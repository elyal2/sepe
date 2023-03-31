package com.fcoalberto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccionesRealizadas implements Serializable {
    private final ArrayList<Accion> datos = new ArrayList();
    private static final Logger log = LogManager.getLogger("rootLogger");
    public static final String ACCIONES_REALIZADAS = "ACCIONES_REALIZADAS";

    public AccionesRealizadas() {
    }

    private String getCell(Row r, int i) throws Exception {
        r.getCell(i).setCellType(CellType.STRING);
        if (r.getCell(i) == null) {
            int var10002 = r.getRowNum() - 1;
            throw new Exception("ROW[" + var10002 + "] CELL[" + i + "] data is null");
        } else {
            return r.getCell(i).getStringCellValue().trim();
        }
    }

    private String getCellDate(Row r, int i) throws Exception {
        if (r.getCell(i) == null) {
            int var10002 = r.getRowNum() - 1;
            throw new Exception("ROW[" + var10002 + "] CELL[" + i + "] data is null");
        } else {
            String pattern = "yyyyMMdd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date dat = r.getCell(i).getDateCellValue();
            return format.format(dat);
        }
    }

    public void addAccion(Row nueva) throws Exception {
        Accion acc = new Accion();

        Logger var10000;
        int var10001;
        try {
            acc.setIdtrabajador(this.getCell(nueva, 0));
        } catch (Exception var15) {
            var10000 = log;
            var10001 = nueva.getRowNum();
            var10000.info("ROW[" + (var10001 + 1) + "] EOF");
            return;
        }

        if (acc.getIdtrabajador().equals("")) {
            var10000 = log;
            var10001 = nueva.getRowNum();
            var10000.info("ROW[" + (var10001 + 1) + "] EOF");
        } else if (acc.getIdtrabajador().length() != 9) {
            var10000 = log;
            var10001 = nueva.getRowNum() + 1;
            var10000.warn("ROW[" + var10001 + "] Saltando fila. ID trabajador no tiene 9 dígitos: " + acc.getIdtrabajador());
            log.warn("<<<< REVISE DATOS AGREGADOS >>>>");
        } else {
            try {
                acc.setNombretrabajador(this.getCell(nueva, 1));
            } catch (Exception var14) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] nombre trabajador: " + var14.getMessage());
                throw var14;
            }

            try {
                acc.setApellido1(this.getCell(nueva, 2));
            } catch (Exception var13) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] apellido: " + var13.getMessage());
                throw var13;
            }

            try {
                acc.setApellido2(this.getCell(nueva, 3));
            } catch (Exception var12) {
                var10000 = log;
                var10001 = nueva.getRowNum();
                var10000.info("ROW[" + (var10001 + 1) + "] no hay segundo apellido");
            }

            try {
                acc.setFechanacimiento(this.getCellDate(nueva, 4));
            } catch (Exception var11) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] nacimiento: " + var11.getMessage());
                throw var11;
            }

            try {
                acc.setSexo(this.getCell(nueva, 5));
            } catch (Exception var10) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] sexo: " + var10.getMessage());
                throw var10;
            }

            try {
                acc.setNivelformativo(this.getCell(nueva, 6));
            } catch (Exception var9) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] nivel: " + var9.getMessage());
                throw var9;
            }

            try {
                acc.setDiscapacidad(this.getCell(nueva, 7));
            } catch (Exception var8) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] discapacidad: " + var8.getMessage());
                throw var8;
            }

            try {
                acc.setInmigrante(this.getCell(nueva, 8));
            } catch (Exception var7) {
                var10000 = log;
                var10001 = nueva.getRowNum() + 1;
                var10000.error("ROW[" + var10001 + "] inmigrante: " + var7.getMessage());
                throw var7;
            }

            String colocacion = "N";

            try {
                colocacion = this.getCell(nueva, 9);
            } catch (NullPointerException var6) {
                var10000 = log;
                var10001 = nueva.getRowNum();
                var10000.info("ROW[" + (var10001 + 1) + "] no hay colocación, default 'N'");
            }

            acc.setColocacion(colocacion);
            if (acc.getColocacion().equals("S")) {
                try {
                    acc.setFechacolocacion(this.getCellDate(nueva, 10));
                    acc.setTipocontrato(this.getCell(nueva, 11));
                    acc.setCifempresa(this.getCell(nueva, 12));
                    acc.setRazonsocial(this.getCell(nueva, 13));
                } catch (Exception var5) {
                    var10000 = log;
                    var10001 = nueva.getRowNum() + 1;
                    var10000.error("ROW[" + var10001 + "] colocación: " + var5.getMessage());
                    throw var5;
                }
            }

            this.datos.add(acc);
        }
    }

    public ArrayList<Accion> getDatos() {
        return this.datos;
    }
}
