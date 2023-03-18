package com.greedy.erp.production.stock.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.production.stock.dto.StockDTO;
import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.production.stock.repository.StockRepository;
import com.greedy.erp.regist.repository.ProductRepository;
import com.greedy.erp.regist.repository.StorageRepository;

@Service
public class StockService {
	
	private static final Logger log = LoggerFactory.getLogger(StockService.class);
	private final StockRepository stockRepository;
	private final StorageRepository storageRepository;
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StockService(
			StockRepository stockRepository,
			StorageRepository storageRepository,
			ProductRepository productRepository,
			ModelMapper modelMapper) {
		this.stockRepository = stockRepository;
		this.storageRepository = storageRepository;
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
	public int selectStockTotal() {
		log.info("[StockService] selectStockTotal Start ==========");
		List<Stock> stockList = stockRepository.findAll();
		log.info("[StockService] selectStockTotal End ==========");
		
		return stockList.size();
	}
	public Object selectStockListWithPaging(Criteria cri) {
		log.info("[StockService] selectStockListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("stockCode").descending());
	        
	    Page<Stock> result = stockRepository.findAll(paging);
	    List<Stock> stockList = (List<Stock>)result.getContent();
	    
	    log.info("[StockService] selectStockListWithPaging End ============");
    
    return stockList.stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
	}
	public Object selectStock(int stockCode) {
		log.info("[StockService] selectStock Start =====");
		
		Stock stock = stockRepository.findById(stockCode).get();
		
		log.info("[StockService] selectStock End =======");
		
        StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
        
		return stockDTO;
	}
	
//	public Object searchStockByType(String search) {
//		List<Stock> searchByType = stockRepository.findByStockTypeContaining(search);
//		return searchByType.stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
//	}
	
	@Transactional
	public Object insertStock(StockDTO stockDTO) {
		log.info("[StockService] ===== insertStock Start =====");
		log.info("[StockService] stockDTO : " + stockDTO);
        
		int result = 0;
		try {	
			Stock stock = modelMapper.map(stockDTO, Stock.class); // 한번에 전체 등록 매핑
			stockRepository.save(stock);
			
			result = 1;
		} catch (Exception e) {
			log.info("[stock] exception");
		}
		
		log.info("[StockService] ====== insertStock End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateStock(StockDTO stockDTO) {
		log.info("[StockService] ===== updateStock Start =====");
		
		int result = 0;
		
		try {
			Stock stock = stockRepository.findById(stockDTO.getStockCode()).get();
			stock.setStockCode(stockDTO.getStockCode());
			stock.setStockAmount(stockDTO.getStockAmount());
			stock.setStorage(storageRepository.findByStorageCode(stockDTO.getStorage().getStorageCode()));
			stock.setProduct(productRepository.findByProductCode(stockDTO.getProduct().getProductCode()));
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[StockService] ====== updateStock End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
		@Transactional
	public Object deleteStock(int stockCode) {
	    int result = 0;
	    
	    try {
	        stockRepository.deleteById(stockCode);
	        result = 1;
	    } catch (Exception e) {
	    }
	    
	    return (result > 0) ? "창고 삭제 성공" : "창고 삭제 실패";
	};
	@Transactional
	public Object deleteStocks(List<Integer> stockCodes) {
	    stockRepository.deleteAllInBatch(stockRepository.findAllById(stockCodes));
	    return stockCodes;
	}
		

};
	

