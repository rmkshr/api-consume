package com.srv.apiconsume.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    /**
     * Endpoint to process an uploaded image/PDF and return extracted text.
     *
     * @param file the uploaded file
     * @return the extracted text in a DTO
     */
    @PostMapping("/process")
    public ResponseEntity<OcrDataDto> processFile(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file locally (Temporary)
            File convertedFile = convertMultiPartToFile(file);

            // Extract text using the OCR Service
            String extractedText = ocrService.extractText(convertedFile);

            // Clean up temporary file
            convertedFile.delete();

            // Return response with DTO
            OcrDataDto ocrData = new OcrDataDto(extractedText);
            return ResponseEntity.ok(ocrData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OcrDataDto("Error processing file: " + e.getMessage()));
        }
    }

    /**
     * Converts a MultipartFile to a standard File object.
     *
     * @param file the MultipartFile to convert
     * @return the converted File
     * @throws IOException if file conversion fails
     */
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = File.createTempFile("upload", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }
}