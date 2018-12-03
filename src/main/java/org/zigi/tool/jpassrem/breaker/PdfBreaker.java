package org.zigi.tool.jpassrem.breaker;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.zigi.tool.jpassrem.exception.InstanceCreationException;

public class PdfBreaker {

	private File pdf;
	private static final Logger LOG = Logger.getLogger(PdfBreaker.class);

	private PdfBreaker(File pdf) {
		this.pdf = pdf;
	}

	public static PdfBreaker getInstance(String filename) throws InstanceCreationException {
		try {
			if (filename == null || filename.isEmpty())
				return null;

			File file = new File(filename);
			if (file.exists() == false)
				return null;

			try {
				PDDocument pdf = PDDocument.load(file);
				if (pdf.isEncrypted() == false)
					return null;
			} catch (Exception ine) {
				return new PdfBreaker(file);
			}

		} catch (Exception e) {
			LOG.warn("Chyba pøi vytváøení instance", e);
		}

		throw new InstanceCreationException("Invalid PDF file");
	}

	public boolean breakPass(String pass) {
		try {
			PDDocument.load(pdf, pass);
			return true;
		} catch (Exception ex) {
			LOG.debug("Heslo není správné", ex);
		}
		return false;
	}
}
