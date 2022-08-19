package com.varxyz.wgt.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class SelectShopController {
	ShopService service = new ShopServiceImpl();
	
	@GetMapping("shop/viewTempMyShop")
	public String viewTempMyShop(Model model, HttpSession session) {
		session.removeAttribute("tempImgList");
		
		String bNum = (String)session.getAttribute("bNum");
		model.addAttribute("shop", service.findShopByBnsNum(bNum));
		model.addAttribute("menus", service.findShopMenuByBnsNum(bNum));
		return "shop/view/viewTempMyShop";
	}
	
	@GetMapping("shop/viewMyShop")
	public String viewMyShop(Model model, HttpSession session) {
		String bNum = (String)session.getAttribute("bNum");
		model.addAttribute("shop", service.findShopByBnsNum(bNum));
		model.addAttribute("menus", service.findShopMenuByBnsNum(bNum));
		return "shop/view/viewMyShop";
	}
	
	@GetMapping("shop/viewUserShop")
	public String viewUserShop(Model model, HttpSession session) {
		model.addAttribute("shop", service.findShopByBnsNum("123-456-789"));
		model.addAttribute("menus", service.findShopMenuByBnsNum("123-456-789"));
		return "shop/view/viewUserShop";
	}
}
