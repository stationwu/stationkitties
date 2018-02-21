package com.cat.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cat.domain.BodyType;
import com.cat.domain.EyeColor;
import com.cat.domain.EyeType;
import com.cat.domain.MouthType;
import com.cat.domain.PatternType;
import com.cat.domain.PrimaryColor;
import com.cat.domain.SecondaryColor;
import com.cat.domain.TertiaryColor;
import com.cat.storage.FileStorageService;

@Component
public class SVGServiceImpl {

	private FileStorageService fileStorageService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public SVGServiceImpl() {
	}

	@Autowired
	public SVGServiceImpl(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	public String generateKittyImage(List<Byte> geneList) throws IOException, TranscoderException {
		BodyType bodyType = BodyType.byOrdinal(geneList.get(19));

		logger.debug("bodyType" + Integer.toString(bodyType.getIndex()) + bodyType.getFileName());

		PatternType patternType = PatternType.byOrdinal(geneList.get(23));

		logger.debug("patternType" + Integer.toString(patternType.getIndex()) + patternType.getPatternName());

		MouthType mouthType = MouthType.byOrdinal(geneList.get(27));

		logger.debug("mouthType" + Integer.toString(mouthType.getIndex()) + mouthType.getFileName());

		EyeType eyeType = EyeType.byOrdinal(geneList.get(31));

		logger.debug("eyeType" + Integer.toString(eyeType.getIndex()) + eyeType.getFileName());

		PrimaryColor primaryColor = PrimaryColor.byOrdinal(geneList.get(35));

		logger.debug("primaryColor" + Integer.toString(primaryColor.getIndex()) + primaryColor.getColor());

		SecondaryColor secondaryColor = SecondaryColor.byOrdinal(geneList.get(39));

		logger.debug("secondaryColor" + Integer.toString(secondaryColor.getIndex()) + secondaryColor.getColor());

		TertiaryColor tertiaryColor = TertiaryColor.byOrdinal(geneList.get(43));

		logger.debug("tertiaryColor" + Integer.toString(tertiaryColor.getIndex()) + tertiaryColor.getColor());

		EyeColor eyeColor = EyeColor.byOrdinal(geneList.get(47));

		logger.debug("eyeColor" + Integer.toString(eyeColor.getIndex()) + eyeColor.getColor());

		String kittyBodyPath = bodyType.getPath() + '-' + patternType.getPatternName();
		String kittyMouthPath = mouthType.getPath();
		String kittyEyePath = eyeType.getPath();

		return this.generateSVG(kittyBodyPath, kittyMouthPath, kittyEyePath, primaryColor, secondaryColor, tertiaryColor,
				eyeColor);
	}

	private String generateSVG(String bodyPath, String mouthPath, String eyePath, PrimaryColor primaryColor,
			SecondaryColor secondaryColor, TertiaryColor tertiaryColor, EyeColor eyeColor)
			throws IOException, TranscoderException {
		Resource bodyResource = this.fileStorageService.loadSVG(bodyPath);
		File bodyFile = bodyResource.getFile();
		String bodyContent = file2String(bodyFile);

		Resource mouthResource = this.fileStorageService.loadSVG(mouthPath);
		File mouthFile = mouthResource.getFile();
		String mouthContent = file2String(mouthFile);

		Resource eyeResource = this.fileStorageService.loadSVG(eyePath);
		File eyeFile = eyeResource.getFile();
		String eyeContent = file2String(eyeFile);

		for (PrimaryColor pColor : PrimaryColor.values()) {
			bodyContent.replaceAll(pColor.toString(), primaryColor.toString());
		}
		for (SecondaryColor sColor : SecondaryColor.values()) {
			bodyContent.replaceAll(sColor.toString(), secondaryColor.toString());
		}
		for (TertiaryColor tColor : TertiaryColor.values()) {
			bodyContent.replaceAll(tColor.toString(), tertiaryColor.toString());
		}
		for (EyeColor eColor : EyeColor.values()) {
			eyeContent.replaceAll(eColor.toString(), eyeColor.toString());
		}

		logger.debug("body step");
		BufferedImage bodyImage = generateImage(bodyContent);
		logger.debug("mouth step");
		BufferedImage mouthImage = generateImage(mouthContent);
		logger.debug("eye step");
		BufferedImage eyeImage = generateImage(eyeContent);
		BufferedImage image = new BufferedImage(400, 400, 2);
		Graphics2D g = image.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.drawImage(bodyImage, 0, 0, bodyImage.getWidth(), bodyImage.getHeight(), null);
		g.drawImage(mouthImage, 0, 0, bodyImage.getWidth(), bodyImage.getHeight(), null);
		g.drawImage(eyeImage, 0, 0, bodyImage.getWidth(), bodyImage.getHeight(), null);
		g.dispose();
		
		return fileStorageService.store(image);
	}

	private BufferedImage generateImage(String content) throws TranscoderException, IOException {
		ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();

		String2Png(content, OutputStream);
		byte[] bytes = OutputStream.toByteArray();
		return ImageIO.read(new ByteArrayInputStream(bytes));
	}

	public static void String2Png(String svgCode, OutputStream outputStream) throws TranscoderException, IOException {
		try {
			byte[] bytes = svgCode.getBytes("utf-8");
			PNGTranscoder t = new PNGTranscoder();
			TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
			TranscoderOutput output = new TranscoderOutput(outputStream);
			t.transcode(input, output);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String file2String(File file) throws IOException {
		int len = 0;
		StringBuffer str = new StringBuffer("");
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null) {
				if (len != 0) // 处理换行符的问题
				{
					str.append("\r\n" + line);
				} else {
					str.append(line);
				}
				len++;
			}
			in.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}

}
