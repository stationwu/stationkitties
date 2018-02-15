package com.cat.web.rest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;


import com.cat.util.SVGServiceImpl;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.GeneRepository;
import com.cat.dao.ImageRepository;
import com.cat.dao.KittyRepository;
import com.cat.domain.Gene;
import com.cat.domain.Image;
import com.cat.domain.Kitty;
import com.cat.domain.dto.KittyContainer;

@RestController
@ExposesResourceFor(Kitty.class)
public class KittyController {
	public static final String PATH = "/Kitties";
	
	@Autowired
	private SVGServiceImpl SVGServiceImpl;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private KittyRepository kittyRepository;
	
	@Autowired
	private GeneRepository geneRepository;
	
	@RequestMapping(path = PATH, method = RequestMethod.GET)
	public KittyContainer getKitty() throws IOException, TranscoderException {
		Kitty kitty = new Kitty();
		Gene gene = new Gene();
		Image image = new Image();
		image.setPath(SVGServiceImpl.generateKittyImage(Arrays.asList(gene.getGenes())));
		kitty.setImage(imageRepository.save(image));
		kitty.setBirthday(LocalDateTime.now());
		kitty.setGene(geneRepository.save(gene));
		kitty.setKittyName("招财猫");
		
		return new KittyContainer(kittyRepository.save(kitty));
	}


}
