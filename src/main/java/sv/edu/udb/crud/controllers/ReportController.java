package sv.edu.udb.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.udb.crud.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    //Log de errores
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportService reportService;

    /**
     * Endpoint para generar el reporte de usuarios.
     * @return ResponseEntity con el reporte PDF.
     */
    @GetMapping("/userReport")
    public ResponseEntity<InputStreamResource> generateUserReport() {
        try {
            // Genera el reporte usando el servicio.
            ByteArrayInputStream bis = reportService.generateUserReport();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=userReport.pdf");

            // Retorna el reporte como respuesta HTTP.
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            logger.error("Ocurrió un error al generar el reporte", e);
            return ResponseEntity.status(500).build();
        }
    }
}
