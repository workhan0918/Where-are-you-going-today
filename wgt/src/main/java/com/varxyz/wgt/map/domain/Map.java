package com.varxyz.wgt.map.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Map {
	private String name;
	private String address;
	private double longitude;
	private	double latitude;
}
