package com.ceatformacion.veterinaria.controller;

import com.ceatformacion.veterinaria.model.Mascota;
import com.ceatformacion.veterinaria.repository.MascotaRepository;

import com.ceatformacion.veterinaria.services.PdfServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

//para generar ficheros de json, pdf
@RestController

public class PDFController {
    //lamada al repositorio
    @Autowired
    private MascotaRepository mascotasRepository;

    @Autowired
    private PdfServices pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPDF() {

        List<Mascota> mascotas = mascotasRepository.findAll();
        ByteArrayInputStream pdfStream = pdfService.exportarMascotas(mascotas);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=mascotas.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfStream.readAllBytes());

    }
}