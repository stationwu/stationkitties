package com.cat.web.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cat.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.ImageRepository;
import com.cat.domain.Image;
import com.cat.domain.Kitty;

@RestController
@ExposesResourceFor(Kitty.class)
public class KittyController {
	public static final String PATH = "/Kitties";
	

	@RequestMapping(path = PATH, method = RequestMethod.GET)
	public void getKitty( ) throws IOException {
		String path = null;
		resp.setContentType(img.getContentType());

		Resource resource = storageService.load(path);
		File file = resource.getFile();
		byte[] content = Files.readAllBytes(file.toPath());

		resp.getOutputStream().write(content);		
	}


}
