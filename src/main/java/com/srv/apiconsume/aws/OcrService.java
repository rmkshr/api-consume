package com.srv.apiconsume.aws;

import net.sourceforge.tess4j.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {

    private final Tesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        // Set Tesseract's data path where language training files like tessdata exist
        tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata/");
        // Set language for OCR processing - default: English ("eng")
        tesseract.setLanguage("eng");
    }

    /**
     * Extracts text from an image or PDF file.
     *
     * @param file the file to process
     * @return the extracted text
     * @throws TesseractException if OCR processing fails
     * @throws IOException        if file handling fails
     */
    public String extractText(File file) throws TesseractException {
        return tesseract.doOCR(file);
    }
}