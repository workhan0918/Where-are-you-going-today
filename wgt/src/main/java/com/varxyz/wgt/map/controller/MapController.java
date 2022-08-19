package com.varxyz.wgt.map.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.wgt.map.domain.Map;
import com.varxyz.wgt.map.service.MapService;
import com.varxyz.wgt.map.service.MapServiceImpl;
import com.varxyz.wgt.shop.domain.Shop;
import com.varxyz.wgt.shop.service.ShopService;
import com.varxyz.wgt.shop.service.ShopServiceImpl;

@Controller("controller.mapController")
public class MapController {
	MapService mapService = new MapServiceImpl();
	ShopService shopService = new ShopServiceImpl();

	@GetMapping("/map/map")
	public String mapForm(Shop shop,Map map,Model model, HttpSession session) {
		
		// 모든 가게조회
		model.addAttribute("shopFind", shopService.findAllShop());
		
		// 경도 위도 불러오기
		model.addAttribute("find", mapService.findAll());
		
		// 아이디 세션
		/*
		 * if(session.getAttribute("userId") == null) {
		 * model.addAttribute("msg","로그인이 필요한 서비스 입니다."); model.addAttribute("url",
		 * "../login"); return "alert/alert"; }
		 */
		model.addAttribute("userId", session.getAttribute("userId"));			

		/*
		 *  여기로 올때 temp 에 올렸던 이미지들을 자동으로 삭제한다.
		 *  2022-08-11 한태우(Shop 담당)
		 */

		// 가게 메뉴 삭제
		if(session.getAttribute("tempShopImg") != null) {
			for (String img : (List<String>)session.getAttribute("tempImgList")) {
				File menuImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg");
				menuImg.delete();
			}
			session.removeAttribute("tempImgList");

			// 가게 이미지 삭제
			String img = (String)session.getAttribute("tempShopImg");
			File shopImg = new File("C:\\wgt\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\temp\\" + img + ".jpg" );
			shopImg.delete();
			session.removeAttribute("tempShopImg");
			// 문제 될시 주석 처리만 해주세용
		}
		return "map/map";
	}

	@PostMapping("/map/map")
	public String map(Shop shop,Map map, Model model) {
		//검색 검색 내용
		model.addAttribute("shopName", shopService.findAllByShopName(shop.getShopName()));
		// 경도 위도 불러오기
		model.addAttribute("find", mapService.findAll());
		//전체 조회
		model.addAttribute("shopFind", shopService.findAllShop());
		return "map/map";
	}
	
	@PostMapping("/map/go_get_waiting")
	public String goGetWaiting() {
		return "redirect:/controller/get_waiting";
	}
	
}
