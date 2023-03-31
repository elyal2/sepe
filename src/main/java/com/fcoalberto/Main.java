package com.fcoalberto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class Main {
    private static final Logger log = LogManager.getLogger("rootLogger");
    public static final String ENVIO_ENPI = "ENVIO_ENPI";
    public static final String XMLNS_NS = "http://www.w3.org/2000/xmlns/";
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String SCHEMA_LOCATION = "XML_ENPI_v1.1.xsd";
    public static final String ENVIO_MENSUAL = "ENVIO_MENSUAL";
    public static final String ENCODING = "ISO-8859-1";
    private static EnvioENPI cabecera;
    private static AccionesRealizadas acciones;
    private static DatosAgregados agregados;

    public Main() {
    }

    public static void main(String[] args) throws Exception {
        String excel = "datos.xlsx";
        if (args.length > 0) {
            excel = args[0];
        }

        log.info("Cargando el fichero " + excel);
        loadExcelFile(excel);
        String xmlfile = "datos.xml";
        if (args.length > 1) {
            xmlfile = args[1];
        }

        log.info("Creando el fichero XML " + xmlfile);
        Document xmlDoc;
        if (cabecera.isAnual()) {
            xmlDoc = crearXMLAnual();
        } else {
            xmlDoc = crearXMLMensual();
        }

        writeXMLfile(xmlfile, xmlDoc);
        FileInputStream xml = new FileInputStream(xmlfile);
        FileInputStream xsd = new FileInputStream("XML_ENPI_v1_1.xsd");
        validateAgainstXSD(xml, xsd);
    }

    static void validateAgainstXSD(InputStream xml, InputStream xsd) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(new StreamSource(xsd));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
        log.info("Validado XSD");
    }

    private static void writeXMLfile(String xmlfile, Document xmlDoc) throws Exception {
        OutputStream output = new FileOutputStream(xmlfile);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty("omit-xml-declaration", "no");
        transformer.setOutputProperty("encoding", "ISO-8859-1");
        transformer.transform(new DOMSource(xmlDoc), new StreamResult(output));
        output.close();
    }

    private static Document crearXMLMensual() throws Exception {
        log.info("Variante mensual");
        Document doc = createDoc();
        Element root = doc.createElement("ENVIO_ENPI");
        addNamespaces(root);
        doc.appendChild(root);
        Element envio = doc.createElement("ENVIO_MENSUAL");
        root.appendChild(envio);
        crearXML(doc, envio);
        return doc;
    }

    private static Document crearXMLAnual() throws Exception {
        log.info("Variante anual");
        Document doc = createDoc();
        Element root = doc.createElement("ENVIO_ENPI");
        addNamespaces(root);
        doc.appendChild(root);
        Element envio = doc.createElement("ENVIO_MENSUAL");
        root.appendChild(envio);
        crearXML(doc, envio);
        return doc;
    }

    private static void crearXML(Document document, Element envio) {
        Element agencia = document.createElement("CODIGO_AGENCIA");
        agencia.appendChild(document.createTextNode(cabecera.getAgencia()));
        envio.appendChild(agencia);
        Element fecha = document.createElement("AÑO_MES_ENVIO");
        fecha.appendChild(document.createTextNode(cabecera.getFecha()));
        envio.appendChild(fecha);
        Element accionesRealizadas = document.createElement("ACCIONES_REALIZADAS");

        Element accion;
        for(Iterator var5 = acciones.getDatos().iterator(); var5.hasNext(); accionesRealizadas.appendChild(accion)) {
            Accion currentAccion = (Accion)var5.next();
            accion = document.createElement("ACCION");
            Element attribute = document.createElement("ID_TRABAJADOR");
            attribute.appendChild(document.createTextNode(currentAccion.getIdtrabajador()));
            accion.appendChild(attribute);
            attribute = document.createElement("NOMBRE_TRABAJADOR");
            attribute.appendChild(document.createTextNode(currentAccion.getNombretrabajador()));
            accion.appendChild(attribute);
            attribute = document.createElement("APELLIDO1_TRABAJADOR");
            attribute.appendChild(document.createTextNode(currentAccion.getApellido1()));
            accion.appendChild(attribute);
            attribute = document.createElement("APELLIDO2_TRABAJADOR");
            String dato = currentAccion.getApellido2();
            if (dato != null) {
                attribute.appendChild(document.createTextNode(dato));
            }

            accion.appendChild(attribute);
            attribute = document.createElement("FECHA_NACIMIENTO");
            attribute.appendChild(document.createTextNode(currentAccion.getFechanacimiento()));
            accion.appendChild(attribute);
            attribute = document.createElement("SEXO_TRABAJADOR");
            attribute.appendChild(document.createTextNode(currentAccion.getSexo()));
            accion.appendChild(attribute);
            attribute = document.createElement("NIVEL_FORMATIVO");
            attribute.appendChild(document.createTextNode(currentAccion.getNivelformativo()));
            accion.appendChild(attribute);
            attribute = document.createElement("DISCAPACIDAD");
            attribute.appendChild(document.createTextNode(currentAccion.getDiscapacidad()));
            accion.appendChild(attribute);
            attribute = document.createElement("INMIGRANTE");
            attribute.appendChild(document.createTextNode(currentAccion.getInmigrante()));
            accion.appendChild(attribute);
            attribute = document.createElement("COLOCACION");
            attribute.appendChild(document.createTextNode(currentAccion.getColocacion()));
            accion.appendChild(attribute);
            if (currentAccion.getColocacion().equals("S")) {
                attribute = document.createElement("FECHA_COLOCACION");
                attribute.appendChild(document.createTextNode(currentAccion.getFechacolocacion()));
                accion.appendChild(attribute);
                attribute = document.createElement("TIPO_CONTRATO");
                attribute.appendChild(document.createTextNode(currentAccion.getTipocontrato()));
                accion.appendChild(attribute);
                attribute = document.createElement("CIF_NIF_EMPRESA");
                attribute.appendChild(document.createTextNode(currentAccion.getCifempresa()));
                accion.appendChild(attribute);
                attribute = document.createElement("RAZON_SOCIAL_EMPRESA");
                attribute.appendChild(document.createTextNode(currentAccion.getRazonsocial()));
                accion.appendChild(attribute);
            }
        }

        envio.appendChild(accionesRealizadas);
        Element datosAgregados = document.createElement("DATOS_AGREGADOS");
        Element attribute = document.createElement("TOTAL_PERSONAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalPersonas()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_NUEVAS_REGISTRADAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalNuevas()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_PERSONAS_PERCEPTORES");
        attribute.appendChild(document.createTextNode(agregados.getTotalPerceptores()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_PERSONAS_INSERCION");
        attribute.appendChild(document.createTextNode(agregados.getTotalInsercion()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_OFERTAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalOfertas()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_OFERTAS_ENVIADAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalEnviadas()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_OFERTAS_CUBIERTAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalCubiertas()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_PUESTOS");
        attribute.appendChild(document.createTextNode(agregados.getTotalPuestos()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_PUESTOS_CUBIERTOS");
        attribute.appendChild(document.createTextNode(agregados.getTotalCubiertos()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_CONTRATOS");
        attribute.appendChild(document.createTextNode(agregados.getTotalContratos()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_CONTRATOS_INDEFINIDOS");
        attribute.appendChild(document.createTextNode(agregados.getTotalIndefinidos()));
        datosAgregados.appendChild(attribute);
        attribute = document.createElement("TOTAL_PERSONAS_COLOCADAS");
        attribute.appendChild(document.createTextNode(agregados.getTotalColocadas()));
        datosAgregados.appendChild(attribute);
        envio.appendChild(datosAgregados);
    }

    private static Document createDoc() throws Exception {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        return document;
    }

    private static void addNamespaces(Element e) {
        Document root = e.getOwnerDocument();
        Attr xsi = root.createAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi");
        xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        Attr schemaLocation = root.createAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:noNamespaceSchemaLocation");
        schemaLocation.setValue("XML_ENPI_v1.1.xsd");
        e.setAttributeNodeNS(xsi);
        e.setAttributeNodeNS(schemaLocation);
    }

    private static void loadExcelFile(String filename) throws Exception {
        FileInputStream excelFile = new FileInputStream(filename);
        Workbook workbook = new XSSFWorkbook(excelFile);
        cabecera = getCabecera(workbook.getSheetAt(0));
        acciones = getAcciones(workbook.getSheetAt(1));
        agregados = getAgregados(workbook.getSheetAt(2));
        excelFile.close();
    }

    private static EnvioENPI getCabecera(Sheet h1) throws Exception {
        EnvioENPI result = new EnvioENPI();
        Row datos = h1.getRow(1);
        datos.getCell(0).setCellType(CellType.STRING);
        datos.getCell(1).setCellType(CellType.STRING);
        result.setAgencia(datos.getCell(0).getStringCellValue());
        result.setFecha(datos.getCell(1).getStringCellValue());
        if (result.getFecha().endsWith("99")) {
            result.setAnual(true);
        }

        return result;
    }

    private static AccionesRealizadas getAcciones(Sheet h1) throws Exception {
        AccionesRealizadas result = new AccionesRealizadas();
        Iterator var2 = h1.iterator();

        while(var2.hasNext()) {
            Row currentRow = (Row)var2.next();
            if (currentRow.getRowNum() != 0) {
                result.addAccion(currentRow);
            }
        }

        return result;
    }

    private static DatosAgregados getAgregados(Sheet h1) {
        DatosAgregados result = new DatosAgregados();
        Row currentRow = h1.getRow(1);
        Iterator var3 = currentRow.iterator();

        while(var3.hasNext()) {
            Cell currentCell = (Cell)var3.next();
            currentCell.setCellType(CellType.STRING);
        }

        result.setTotalPersonas(currentRow.getCell(0).getStringCellValue());
        result.setTotalNuevas(currentRow.getCell(1).getStringCellValue());
        result.setTotalPerceptores(currentRow.getCell(2).getStringCellValue());
        result.setTotalInsercion(currentRow.getCell(3).getStringCellValue());
        result.setTotalOfertas(currentRow.getCell(4).getStringCellValue());
        result.setTotalEnviadas(currentRow.getCell(5).getStringCellValue());
        result.setTotalCubiertas(currentRow.getCell(6).getStringCellValue());
        result.setTotalPuestos(currentRow.getCell(7).getStringCellValue());
        result.setTotalCubiertos(currentRow.getCell(8).getStringCellValue());
        result.setTotalContratos(currentRow.getCell(9).getStringCellValue());
        result.setTotalIndefinidos(currentRow.getCell(10).getStringCellValue());
        result.setTotalColocadas(currentRow.getCell(11).getStringCellValue());
        return result;
    }
}
