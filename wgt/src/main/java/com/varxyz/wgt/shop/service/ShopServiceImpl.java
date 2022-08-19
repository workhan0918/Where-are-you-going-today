package com.varxyz.wgt.shop.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.wgt.data.DataSourceConfig;
import com.varxyz.wgt.shop.dao.ShopDao;
import com.varxyz.wgt.shop.domain.Menu;
import com.varxyz.wgt.shop.domain.MenuCommand;
import com.varxyz.wgt.shop.domain.Shop;

public class ShopServiceImpl implements ShopService {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	ShopDao dao = context.getBean("shopDao", ShopDao.class);

	@Override
	public Shop findAllByShopName(String shopName) {
		return dao.findAllByShopName(shopName);
	}

	@Override
	public boolean addShop(Shop shop) {
		return dao.addShop(shop);
	}

	@Override
	public boolean addMenu(Menu menu) {
		return dao.addMenu(menu);
	}

	@Override
	public Shop findShopByBnsNum(String bnsNum) {
		return dao.findShopByBnsNum(bnsNum);
	}

	@Override
	public List<Menu> findShopMenuByBnsNum(String bnsNum) {
		return dao.findShopMenuByBnsNum(bnsNum);
	}

	@Override
	public boolean updateShopMenu(MenuCommand updatedMenu, Menu oldMenu) {
		return dao.updateShopMenu(updatedMenu, oldMenu);
	}

	@Override
	public boolean deleteShopMenu(String menuName, String menuImg) {
		return dao.deleteShopMenu(menuName, menuImg);
	}

	@Override
	public Menu findMenuByMenuName(String menuName) {
		return dao.findMenuByMenuName(menuName);
	}

	@Override
	public boolean updateShop(Shop shop, String oldImg) {
		return dao.updateShop(shop, oldImg);
	}

	@Override
	public List<Shop> findAllShop() {
		return dao.findAllShop();
	}
}
