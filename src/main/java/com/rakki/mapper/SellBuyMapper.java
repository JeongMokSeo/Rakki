package com.rakki.mapper;

import org.apache.ibatis.annotations.Param;

import com.rakki.domain.BuyVo;
import com.rakki.domain.PriceListVo;
import com.rakki.domain.SellVo;

public interface SellBuyMapper {
	
	//상품품목키, 사이즈별 구입 가격리스트 취득
	PriceListVo getBuyPriceList(Long product_no);
	
	//상품품목키, 사이즈별 판매 가격리스트 취득
	PriceListVo getSellPriceList(Long product_no);
	
	//판매 체크용 화면 정보취득
	PriceListVo getSellCheck(Long product_no);
	
	//판매 확인용 화면 정보취득
	PriceListVo getSellConfirm(@Param("product_no") Long product_no,@Param("size_type") String size_types);
	
	//판매입찰,즉시판매 등록용
	void insertSellGoods(SellVo sell);
	
	//즉시판매 등록용
	void insertSellTrade(SellVo sell);
	
	//판매입찰용
	void insertSellGoods_NullTradeNo(SellVo sell);
	
	//구매자 상품등록번호 취득	
	Long getBuyGoodsNo(SellVo sell);
	
	//새 상품등록번호 취득
	Long getNewGoodsNo();

	//즉시판매시 구매자 상품정보 갱신
	void updateBuyGoods(SellVo sell);

	//판매자 상품등록번호 취득
	Long getSellerGoodsNo(BuyVo buy);

	//거래번호 취득
	Long getNewTradeNo();
	
	//즉시구매 등록용 (거래정보)
	void insertBuyTrade(BuyVo buy);

	//즉시구매 등록용 (상품정보)
	void insertBuyGoods(BuyVo buy);

	//즉시구매시 판매자 상품정보 갱신
	void updateSellGoods(BuyVo buy);

	//구매입찰용 등록용(상품정보)
	void insertBuyGoods_NullTradeNo(BuyVo buy);
	
}

