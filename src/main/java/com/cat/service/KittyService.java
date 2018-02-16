package com.cat.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import javax.transaction.Transactional;

import com.cat.util.SVGServiceImpl;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.GeneRepository;
import com.cat.dao.ImageRepository;
import com.cat.dao.KittyRepository;
import com.cat.domain.BodyType;
import com.cat.domain.EyeColor;
import com.cat.domain.EyeType;
import com.cat.domain.Gene;
import com.cat.domain.Image;
import com.cat.domain.Kitty;
import com.cat.domain.MouthType;
import com.cat.domain.PatternType;
import com.cat.domain.PrimaryColor;
import com.cat.domain.SecondaryColor;
import com.cat.domain.TertiaryColor;
import com.cat.domain.dto.KittyContainer;

@Service
public class KittyService {

	@Autowired
	private SVGServiceImpl SVGServiceImpl;

	@Autowired
	private KittyRepository kittyRepository;

	@Transactional
	public Kitty getRaddomKitty() throws IOException, TranscoderException {
		Kitty kitty = new Kitty();
		Gene gene = new Gene();
		Image image = new Image();
		image.setPath(SVGServiceImpl.generateKittyImage(Arrays.asList(gene.getGenes())));
		kitty.setImage(image);
		kitty.setBirthday(LocalDateTime.now());
		kitty.setGene(gene);
		kitty.setKittyName("招财猫");
		Kitty entity = kittyRepository.save(kitty);
		entity.getGene().setKitty(entity);
		entity.getImage().setKitty(entity);
		return kittyRepository.save(entity);
	}
	
	@Transactional
	public Kitty getBabyKitty(Kitty mKitty, Kitty sKitty) throws IOException, TranscoderException {
		Kitty kitty = new Kitty();
		Gene gene = new Gene(mKitty, sKitty);
		Image image = new Image();
		image.setPath(SVGServiceImpl.generateKittyImage(Arrays.asList(gene.getGenes())));
		kitty.setImage(image);
		kitty.setBirthday(LocalDateTime.now());
		kitty.setGene(gene);
		kitty.setKittyName("招财猫");
		kitty.setMatron(mKitty);
		kitty.setSire(sKitty);
		Kitty entity = kittyRepository.save(kitty);
		entity.getGene().setKitty(entity);
		entity.getImage().setKitty(entity);
		return kittyRepository.save(entity);
	}
	
	
}
