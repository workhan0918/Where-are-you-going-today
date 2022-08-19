package com.varxyz.wgt.shop.service;

import java.util.List;

import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.domain.MenuCommand;
import com.varxyz.wgt.shop.domain.Shop;

public interface ShopService {
	// 매장정보 모두 불러오기
	public List<Shop> findAllShop();
	
	// 매장명으로 매장 정보 가져오기
	public Shop findAllByShopName(String shopName);
	
	// 매장 추가
	public boolean addShop(Shop shop);
	
	// 매장 메뉴 추가
	public boolean addMenu(Menu menu);
	
	// 자신의 매장 조회 ( 사업자 번호로 )
	public Shop findShopByBnsNum(String bnsNum);
	
	// 매장 메뉴 조회 ( 사업자 번호로 )
	public List<Menu> findShopMenuByBnsNum(String bnsNum);
	
	// 메뉴 개별 수정
	public boolean updateShopMenu(MenuCommand updatedMenu, Menu oldMenu);
	
	// 메뉴 개별 삭제
	public boolean deleteShopMenu(String menuName, String menuImg);
	
	// 메뉴이름으로 메뉴 검색
	public Menu findMenuByMenuName(String menuName);
	
	// 매장 정보 수정
	public boolean updateShop(Shop shop, String oldImg);
}
