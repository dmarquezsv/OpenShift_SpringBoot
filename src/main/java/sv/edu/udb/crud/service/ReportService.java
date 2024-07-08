package sv.edu.udb.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import sv.edu.udb.crud.model.User;
import sv.edu.udb.crud.repository.UserRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Genera un reporte PDF de los usuarios.
     * @return ByteArrayInputStream con el contenido del reporte en PDF.
     * @throws JRException si ocurre un error durante la generación del reporte.
     */
    public ByteArrayInputStream generateUserReport() throws JRException {
        // Obtiene todos los usuarios de la base de datos.
        List<User> users = userRepository.findAll();

        // Crea una fuente de datos de JasperReports a partir de la lista de usuarios.
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

        // Parámetros adicionales para el reporte.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Demo jasperreports");

        // Carga y compila el diseño del reporte.
        InputStream reportStream = getClass().getResourceAsStream("/reports/userReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Llena el reporte con los datos y parámetros.
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exporta el reporte a PDF.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        // Retorna el PDF como un ByteArrayInputStream.
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
