package com.cat.web.rest;

import java.io.IOException;
import java.util.Arrays;


import com.cat.util.SVGServiceImpl;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.domain.Gene;
import com.cat.domain.Kitty;

@RestController
@ExposesResourceFor(Kitty.class)
public class KittyController {
	public static final String PATH = "/Kitties";
	
	@Autowired
	private SVGServiceImpl SVGServiceImpl;
	
	@RequestMapping(path = PATH, method = RequestMethod.GET)
	public void getKitty() throws IOException, TranscoderException {
		Gene gene = new Gene();
		SVGServiceImpl.generateKittyImage(Arrays.asList(gene.getGenes()));
	}


}
