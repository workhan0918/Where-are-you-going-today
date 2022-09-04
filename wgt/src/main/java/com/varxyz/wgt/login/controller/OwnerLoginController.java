package com.varxyz.wgt.login.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.login.service.OwnerLoginService;
import com.varxyz.wgt.login.serviceImpl.OwnerLoginServiceImpl;
import com.varxyz.wgt.owner.doamin.Owner;
import com.varxyz.wgt.owner.service.OwnerService;
import com.varxyz.wgt.owner.serviceImpl.OwnerServiceImpl;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller
public class OwnerLoginController {
	OwnerLoginService ownerLoginService = new OwnerLoginServiceImpl();

	OwnerService ownerService = new OwnerServiceImpl();

	// 점주 로그인
	@GetMapping("/ownerLogin")
	public String ownerForm(HttpSession session) {
		/*
		 * 여기로 올때 temp 에 올렸던 이미지들을 자동으로 삭제한다. 2022-08-11 한태우(Shop 담당)
		 */
		// 가게 메뉴 삭제
		if (session.getAttribute("tempShopImg") != null) {
			for (String img : (List<String>) session.getAttribute("tempImgList")) {
				File menuImg = new File(
						"C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
				menuImg.delete();
			}
			session.invalidate();	// 세션 단절
			
			// 가게 이미지 삭제
			String img = (String) session.getAttribute("tempShopImg");
			File shopImg = new File(
					"C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
			shopImg.delete();
			session.removeAttribute("tempShopImg");
			// 문제 될시 주석 처리만 해주세용
		}
		return "login/ownerLogin";

	}

	@PostMapping("/ownerLogin")
	public String ownerLogin(Owner owner, HttpSession session, HttpServletRequest request,
							Model model) {

		Owner dbOwner = new Owner();
		dbOwner = ownerService.findAllOwner(owner.getOwnerId());
		session.setAttribute("dbOwner", dbOwner);
		
			if(owner.getOwnerId().equals(dbOwner.getOwnerId()) &&
					owner.getPasswd().equals(dbOwner.getPasswd())) {
				
				ShopService shopService = new ShopServiceImpl();

				session.setAttribute("bnsNum", shopService.findShopByOwnerId(owner.getOwnerId()).getBusinessNumber());
				session.setAttribute("ownerId", request.getParameter("ownerId"));
				return "redirect:/add_shop";
			} else if(!owner.getOwnerId().equals(dbOwner.getOwnerId())) {
				
				model.addAttribute("msg", "아이디를 다시 확인하세요!!");
				
				return "error/error";
		} 
		
		model.addAttribute("msg", "비밀번호를 다시 확인해주세요!!");
		 
		return "error/error";

	}
	// 로그아웃
	@GetMapping("/ownerLogOut")
	public String ownerLogOutForm() {

		return "logOut/ownerLogOut";
	}

	@PostMapping("ownerLogOut")
	public String ownerLogOut(HttpSession session) {
		session.invalidate(); // 세션단절

		return "login/ownerLogin";
	}


}
