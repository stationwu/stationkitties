package com.cat.web.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.CustomerRepository;
import com.cat.dao.KittyRepository;
import com.cat.domain.Customer;
import com.cat.domain.Kitty;
import com.cat.domain.dto.CustomerContainer;
import com.cat.service.KittyService;

@RestController
public class CustomerController {
	public static final String PATH = "/User";

	public static final String USER_PATH = PATH + "/{code}";

	@Autowired
	private KittyService kittyService;

	@Autowired
	private KittyRepository kittyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(path = USER_PATH, method = RequestMethod.GET)
	@Transactional
	public CustomerContainer login(@PathVariable String code, HttpSession session)
			throws Exception, IOException, TranscoderException {
		Customer customer = null;
		String openCode = null;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", "wxe59cf03c85cab017");
		paramMap.put("secret", "d219f034d73c5289299393aa661aebae");
		paramMap.put("js_code", code);
		paramMap.put("grant_type", "authorization_code");
		List<NameValuePair> list = new LinkedList<NameValuePair>();
		for (Entry<String, String> entry : paramMap.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/sns/jscode2session");
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "utf-8");
		httpPost.setEntity(formEntity);
		HttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpresponse = null;
		try {
			httpresponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpresponse.getEntity();
			String response = EntityUtils.toString(httpEntity, "utf-8");
			if (response.indexOf("openid") < 0) {
				throw new Exception("请使用微信登录");
			} else {
				openCode = response.substring(response.indexOf("openid") + 9, response.length() - 2);
			}
		} catch (ClientProtocolException e) {
			System.out.println("http请求失败，uri{},exception{}");
		} catch (IOException e) {
			System.out.println("http请求失败，uri{},exception{}");
		}

		if (!customerRepository.isCustomerAlreadyRegistered(openCode)) {
			customer = new Customer(openCode);
			Kitty kitty = kittyService.getRaddomKitty();
			customer.addKitties(kitty);
			customer = customerRepository.save(customer);
			kitty.setCustomer(customer);
			kitty.setForSale(false);
			kittyRepository.save(kitty);
		} else {
			customer = customerRepository.findOneByOpenCode(openCode);
			Random number = new Random();
			List<Kitty> mKitties = customer.getKitties().stream().filter(x -> x.getGender() == Kitty.Gender.MALE)
					.collect(Collectors.toList());
			List<Kitty> sKitties = customer.getKitties().stream().filter(x -> x.getGender() == Kitty.Gender.FEMALE)
					.collect(Collectors.toList());
			if (number.nextInt(100) < 10 && customer.getKitties().size() < 9) {
				Kitty mKitty = mKitties.get(number.nextInt(mKitties.size()));
				Kitty sKitty = sKitties.get(number.nextInt(sKitties.size()));
				Kitty babyKittiy = kittyService.getBabyKitty(mKitty, sKitty);
				babyKittiy.setCustomer(customer);
				babyKittiy = kittyRepository.save(babyKittiy);
				customer.addKitties(babyKittiy);
			}
		}
		session.setAttribute("OpenCode", openCode);
		CustomerContainer customerContainer = new CustomerContainer(customer);
		return customerContainer;
	}

	@RequestMapping(path = USER_PATH, method = RequestMethod.POST)
	@Transactional
	public CustomerContainer getDetail(@PathVariable String code) throws Exception, IOException, TranscoderException {
		Customer customer = null;
		String openCode = code;
		customer = customerRepository.findOneByOpenCode(openCode);

		CustomerContainer customerContainer = new CustomerContainer(customer);
		return customerContainer;
	}

}
