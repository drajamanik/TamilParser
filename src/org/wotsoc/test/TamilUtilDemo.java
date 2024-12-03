package org.wotsoc.test;

import org.wotsoc.illakanam.TamilUtil;

public class TamilUtilDemo {
	
	public static void main(String args[]) {
		System.out.println(TamilUtil.எழுத்துகளைபிரி("சுபாஷினி"));
		System.out.println(TamilUtil.எழுத்துகளைபிரி("சுமலதா"));
		System.out.println(TamilUtil.கடையெழுத்துஉயிரெழுதா(TamilUtil.எழுத்துகளைபிரி("சுபாஷினி")));
		System.out.println(TamilUtil.கடையெழுத்துஉயிரெழுதா(TamilUtil.எழுத்துகளைபிரி("சுமலதா")));
	}
}
