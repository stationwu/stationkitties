package com.cat.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.cat.domain.EyeColor;
import com.cat.domain.PrimaryColor;
import com.cat.domain.SecondaryColor;
import com.cat.domain.TertiaryColor;
import com.cat.storage.FileStorageService;

public class SVGServiceImpl {

	private FileStorageService fileStorageService;

	public SVGServiceImpl() {
	}

	@Autowired
	public SVGServiceImpl(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	public OutputStream generateSVG(String bodyPath, String mouthPath, String eyePath, PrimaryColor primaryColor,
			SecondaryColor secondaryColor, TertiaryColor tertiaryColor, EyeColor eyeColor) throws IOException {
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
		
		Resource bodyResource = this.fileStorageService.load(bodyPath);
		File bodyFile = bodyResource.getFile();
		Document bodyDoc = f.createDocument(bodyFile.toURI().toString());
		
		Resource mouthResource = this.fileStorageService.load(mouthPath);
		File mouthFile = mouthResource.getFile();
		Document mouthDoc = f.createDocument(mouthFile.toURI().toString());
		
		Resource eyeResource = this.fileStorageService.load(eyePath);
		File eyeFile = eyeResource.getFile();
		Document eyeDoc = f.createDocument(eyeFile.toURI().toString());
		
		String bodyContent = bodyDoc.getTextContent();
		bodyContent.replaceAll(regex, replacement)
		bodyDoc.setTextContent(textContent);
			
	}

}
