package com.greedy.erp.production.purchase.service;

import java.util.ArrayList;
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

import com.greedy.erp.authority.repository.EmpRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.production.purchase.dto.PurchaseDTO;
import com.greedy.erp.production.purchase.dto.PurchaseDetailDTO;
import com.greedy.erp.production.purchase.dto.RequestDTO;
import com.greedy.erp.production.purchase.entity.Purchase;
import com.greedy.erp.production.purchase.entity.PurchaseDetail;
import com.greedy.erp.production.business.dto.SalesDTO;
import com.greedy.erp.production.business.entity.Sales;
import com.greedy.erp.production.business.entity.SalesDetail;
import com.greedy.erp.production.purchase.dto.PlaceDTO;
import com.greedy.erp.production.purchase.entity.Request;
import com.greedy.erp.production.purchase.entity.Place;
import com.greedy.erp.production.purchase.repository.RequestRepository;
import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.production.stock.repository.StockRepository;
import com.greedy.erp.production.purchase.repository.PlaceRepository;
import com.greedy.erp.production.purchase.repository.PurchaseRepository;
import com.greedy.erp.regist.repository.ClientRepository;
import com.greedy.erp.regist.repository.ProductRepository;
import com.greedy.erp.regist.repository.StorageRepository;


@Service
public class PurchaseService {
	
	private static final Logger log = LoggerFactory.getLogger(PurchaseService.class);
	private final RequestRepository requestRepository;
	private final PlaceRepository placeRepository;
	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;
	
	private final ClientRepository clientRepository;
	private final EmpRepository empRepository;
	private final StorageRepository storageRepository;
	private final StockRepository stockRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public PurchaseService(
			RequestRepository requestRepository,
			PlaceRepository placeRepository,
			PurchaseRepository purchaseRepository,
			ProductRepository productRepository,
			
			ClientRepository clientRepository,
			EmpRepository empRepository,
			StorageRepository storageRepository,
			StockRepository stockRepository,
			
			ModelMapper modelMapper) {
		
		this.requestRepository = requestRepository;
		this.placeRepository = placeRepository;
		this.purchaseRepository = purchaseRepository;
		this.productRepository = productRepository;
		
		this.clientRepository = clientRepository;
		this.empRepository = empRepository;
		this.storageRepository = storageRepository;
		this.stockRepository = stockRepository;
		
		this.modelMapper = modelMapper;
	}
	
	/* 요청 */
	
	public int selectRequestTotal() {
		log.info("[RequestService] selectRequestTotal Start ==========");
		List<Request> requestList = requestRepository.findAll();
		log.info("[RequestService] selectRequestTotal End ==========");
		
		return requestList.size();
	}
	
	public Object selectRequestListWithPaging(Criteria cri) {
		log.info("[RequestService] selectRequestListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("requestCode").descending());
	        
	    Page<Request> result = requestRepository.findAll(paging);
	    List<Request> requestList = (List<Request>)result.getContent();
	    
	    log.info("[RequestService] selectRequestListWithPaging End ============");
    
    return requestList.stream().map(request -> modelMapper.map(request, RequestDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectRequest(int requestCode) {
		log.info("[RequestService] selectRequest Start =====");
		
		Request request = requestRepository.findById(requestCode).get();
		
		log.info("[RequestService] selectRequest End =======");
		
        RequestDTO requestDTO = modelMapper.map(request, RequestDTO.class);
        
		return requestDTO;
	}
	
	@Transactional
	public Object insertRequest(RequestDTO requestDTO) {
		log.info("[RequestService] ===== insertRequest Start =====");
		log.info("[RequestService] requestDTO : " + requestDTO);

		int result = 0;
		
		try {	
			Request request = modelMapper.map(requestDTO, Request.class);
			requestRepository.save(request);

			result = 1;
		} catch (Exception e) {
			
			log.info("[request] exception");
		}
		
		log.info("[RequestService] ====== insertRequest End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateRequest(RequestDTO requestDTO) {
		log.info("[RequestService] ===== updateRequest Start =====");
		
		int result = 0;
		
		try {
			Request request = requestRepository.findById(requestDTO.getRequestCode()).get();
			request.setRequestCode(requestDTO.getRequestCode());
			request.setRequestStatus(requestDTO.getRequestStatus());
			request.setClient(clientRepository.findByClientCode(requestDTO.getClient().getClientCode()));
			request.setEmp(empRepository.findByEmpCode(requestDTO.getEmp().getEmpCode()));
			request.setStorage(storageRepository.findByStorageCode(requestDTO.getStorage().getStorageCode()));
			request.setRequestDetail(requestDTO.getRequestDetail());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[RequestService] ====== updateRequest End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
	
	@Transactional
	public Object deleteRequest(Integer requestCode) {
	    log.info("[RequestService] ===== deleteRequest Start =====");
	    
	    int result = 0;
	    
	    try {
	        requestRepository.deleteById(requestCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[request delete] Exception!!");
	    }
	    
	    log.info("[RequestService] ====== deleteRequest End ======");
	    return (result > 0) ? "요청서 삭제 성공" : "요청서 삭제 실패";
	}
		
		
		
		
		
		
		
		
		
		
		
		
	
	/* 주문 */
	
	public int selectPlaceTotal() {
		log.info("[PlaceService] selectPlaceTotal Start ==========");
		List<Place> placeList = placeRepository.findAll();
		log.info("[PlaceService] selectPlaceTotal End ==========");
		
		return placeList.size();
	}
	
	public Object selectPlaceListWithPaging(Criteria cri) {
		log.info("[PlaceService] selectPlaceListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("placeCode").descending());
	        
	    Page<Place> result = placeRepository.findAll(paging);
	    List<Place> placeList = (List<Place>)result.getContent();
	    
	    log.info("[PlaceService] selectPlaceListWithPaging End ============");
    
    return placeList.stream().map(place -> modelMapper.map(place, PlaceDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectPlace(int placeCode) {
		log.info("[PlaceService] selectPlace Start =====");
		
		Place place = placeRepository.findById(placeCode).get();
		
		log.info("[PlaceService] selectplace End =======");
		
        PlaceDTO placeDTO = modelMapper.map(place, PlaceDTO.class);
        
		return placeDTO;
	}
	
	@Transactional
	public Object insertPlace(PlaceDTO placeDTO) {
		log.info("[PlaceService] ===== insertPlace Start =====");
		log.info("[PlaceService] placeDTO : " + placeDTO);

		int result = 0;
		
		try {	
			Place place = modelMapper.map(placeDTO, Place.class);
			placeRepository.save(place);

			result = 1;
		} catch (Exception e) {
			
			log.info("[place] exception");
		}
		
		log.info("[PlaceService] ====== insertPlace End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updatePlace(PlaceDTO placeDTO) {
		log.info("[PlaceService] ===== updatePlace Start =====");
		
		int result = 0;
		
		try {
			Place place = placeRepository.findById(placeDTO.getPlaceCode()).get();
			place.setPlaceCode(placeDTO.getPlaceCode());
			place.setPlaceDelivery(placeDTO.getPlaceDelivery());
			place.setPlaceStatus(placeDTO.getPlaceStatus());
			place.setClient(clientRepository.findByClientCode(placeDTO.getClient().getClientCode()));
			place.setEmp(empRepository.findByEmpCode(placeDTO.getEmp().getEmpCode()));
			place.setStorage(storageRepository.findByStorageCode(placeDTO.getStorage().getStorageCode()));
			place.setPlaceDetail(placeDTO.getPlaceDetail());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		
		log.info("[PlaceService] ====== updatePlace End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
	
	@Transactional
	public Object deletePlace(Integer placeCode) {
	    log.info("[PlaceService] ===== deletePlace Start =====");
	    
	    int result = 0;
	    
	    try {
	        placeRepository.deleteById(placeCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[place delete] Exception!!");
	    }
	    
	    log.info("[PlaceService] ====== deletePlace End ======");
	    return (result > 0) ? "발주서 삭제 성공" : "발주서 삭제 실패";
	}
	
	

	public int selectPurchaseTotal() {
		log.info("[PurchaseService] selectPurchaseTotal Start ==========");
		List<Purchase> purchaseList = purchaseRepository.findAll();
		log.info("[PurchaseService] selectPurchaseTotal End ==========");
		return purchaseList.size();
	}
	
	public Object selectPurchaseListWithPaging(Criteria cri) {
		log.info("[PurchaseService] selectPurchaseListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("purchaseCode").descending());
	    Page<Purchase> result = purchaseRepository.findAll(paging);
	    List<Purchase> purchaseList = (List<Purchase>)result.getContent();
	    log.info("[PurchaseService] selectPurchaseListWithPaging End ============");
    return purchaseList.stream().map(purchase -> modelMapper.map(purchase, PurchaseDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectPurchase(int purchaseCode) {
		log.info("[PurchaseService] selectPurchase Start =====");
		Purchase purchase = purchaseRepository.findById(purchaseCode).get();
		log.info("[PurchaseService] selectPurchase End =======");
        PurchaseDTO purchaseDTO = modelMapper.map(purchase, PurchaseDTO.class);
		return purchaseDTO;
	}

	@Transactional
	public Object insertPurchase(PurchaseDTO purchaseDTO) {
	    log.info("[PurchaseService] ===== insertPurchase Start =====");
	    log.info("[PurchaseService] purchaseDTO : " + purchaseDTO);
	    int result = 0;
	    try {   
	        Purchase purchase = new Purchase();
	        purchase.setPurchaseCode(purchaseDTO.getPurchaseCode());
	        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
	        purchase.setClient(clientRepository.findByClientCode(purchaseDTO.getClient().getClientCode()));
	        purchase.setEmp(empRepository.findByEmpCode(purchaseDTO.getEmp().getEmpCode()));
	        
	        List<PurchaseDetail> purchaseDetailList = new ArrayList<>();
	        for(PurchaseDetail purchaseDetailDTO : purchaseDTO.getPurchaseDetail()) {
	            PurchaseDetail purchaseDetail = new PurchaseDetail();
	            purchaseDetail.setPurchaseNo(purchaseDetailDTO.getPurchaseNo());
	            purchaseDetail.setStock(stockRepository.findByStockCode(purchaseDetailDTO.getStock().getStockCode()));
	            purchaseDetail.setPurchaseAmount(purchaseDetailDTO.getPurchaseAmount());
	            purchaseDetail.setPurchaseNote(purchaseDetailDTO.getPurchaseNote());
	            purchaseDetailList.add(purchaseDetail);
	            
	            Stock stock = stockRepository.findByStockCode(purchaseDetailDTO.getStock().getStockCode());
	            stock.setStockAmount(stock.getStockAmount() - purchaseDetailDTO.getPurchaseAmount());
	            stockRepository.save(stock);
	            
	        }
	        purchase.setPurchaseDetail(purchaseDetailList);
	        purchaseRepository.save(purchase);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[purchase] exception");
	    }
	    log.info("[PurchaseService] ====== insertPurchase End ======");
	    return (result > 0)? "판매 등록 성공" : "판매 등록 실패";
	}
	
	
	
	
	
	
	
	
}
