package org.springframework.samples.petclinic.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SecurityController {

	private static Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

	@GetMapping("/statususer")
	public String mainWithParam(
		@RequestParam(name = "name", required = false, defaultValue = "")
			String name, Model model, Authentication authentication) {

		LOGGER.info("statususer");
		LOGGER.info("authentication={}", authentication);

		model.addAttribute("status", "aaa");
		model.addAttribute("authent", authentication != null && authentication.isAuthenticated());

		return "statususer"; //view
	}


}
