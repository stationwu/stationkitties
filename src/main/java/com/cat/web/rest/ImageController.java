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

@RestController
@ExposesResourceFor(Image.class)
public class ImageController {
	public static final String PATH = "/Images";
	
	public static final String IMAGE_PATH = PATH + "/{id}";
	
	public static final String THUMBNAIL_PATH = PATH + "/{id}/thumbnail";
	private final ImageRepository imageRepository;
	private final FileStorageService storageService;

	@Autowired
	public ImageController(ImageRepository imageRepository,
						   FileStorageService fileStorageService) {
		this.imageRepository = imageRepository;
		this.storageService = fileStorageService;
	}

	@RequestMapping(path = IMAGE_PATH, method = RequestMethod.GET)
	public void getImage(@PathVariable Long id, HttpServletResponse resp, HttpSession session) throws IOException {
		Image img = imageRepository.findOne(id);

		// Interceptor already checked it's not null
		String path = img.getPath();
		resp.setContentType("image/png");

		Resource resource = storageService.load(path);
		File file = resource.getFile();
		byte[] content = Files.readAllBytes(file.toPath());

		resp.getOutputStream().write(content);
	}

}
