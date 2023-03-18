package com.greedy.erp.production.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.greedy.erp.production.business.dto.EstimateDTO;
import com.greedy.erp.production.business.dto.OrdersDTO;
import com.greedy.erp.production.business.dto.SalesDTO;
import com.greedy.erp.production.business.dto.SalesDetailDTO;
import com.greedy.erp.production.business.entity.Estimate;
import com.greedy.erp.production.business.entity.Orders;
import com.greedy.erp.production.business.entity.Sales;
import com.greedy.erp.production.business.entity.SalesDetail;
import com.greedy.erp.production.business.repository.EstimateRepository;
import com.greedy.erp.production.business.repository.OrdersRepository;
import com.greedy.erp.production.business.repository.SalesRepository;
import com.greedy.erp.production.stock.entity.Stock;
import com.greedy.erp.production.stock.repository.StockRepository;
import com.greedy.erp.regist.repository.ClientRepository;
import com.greedy.erp.regist.repository.ProductRepository;
import com.greedy.erp.regist.repository.StorageRepository;

@Service
public class BusinessService {
	
	private static final Logger log = LoggerFactory.getLogger(BusinessService.class);
	private final EstimateRepository estimateRepository;
	private final OrdersRepository ordersRepository;
	private final SalesRepository salesRepository;
	private final ProductRepository productRepository;
	
	private final ClientRepository clientRepository;
	private final EmpRepository empRepository;
	private final StorageRepository storageRepository;
	private final StockRepository stockRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public BusinessService(
			EstimateRepository estimateRepository,
			OrdersRepository ordersRepository,
			SalesRepository salesRepository,
			ProductRepository productRepository,
			
			ClientRepository clientRepository,
			EmpRepository empRepository,
			StorageRepository storageRepository,
			StockRepository stockRepository,
			
			ModelMapper modelMapper) {
		
		this.estimateRepository = estimateRepository;
		this.ordersRepository = ordersRepository;
		this.salesRepository = salesRepository;
		this.productRepository = productRepository;
		
		this.clientRepository = clientRepository;
		this.empRepository = empRepository;
		this.storageRepository = storageRepository;
		this.stockRepository = stockRepository;
		
		this.modelMapper = modelMapper;
	}
	
	/* 견적 */
	public int selectEstimateTotal() {
		log.info("[EstimateService] selectEstimateTotal Start ==========");
		List<Estimate> estimateList = estimateRepository.findAll();
		log.info("[EstimateService] selectEstimateTotal End ==========");
		return estimateList.size();
	}
	
	public Object selectEstimateListWithPaging(Criteria cri) {
		log.info("[EstimateService] selectEstimateListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("estimateCode").descending());
	    Page<Estimate> result = estimateRepository.findAll(paging);
	    List<Estimate> estimateList = (List<Estimate>)result.getContent();
	    log.info("[EstimateService] selectEstimateListWithPaging End ============");
    return estimateList.stream().map(estimate -> modelMapper.map(estimate, EstimateDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectEstimate(int estimateCode) {
		log.info("[EstimateService] selectEstimate Start =====");
		Estimate estimate = estimateRepository.findById(estimateCode).get();
		log.info("[EstimateService] selectEstimate End =======");
        EstimateDTO estimateDTO = modelMapper.map(estimate, EstimateDTO.class);
		return estimateDTO;
	}
	
	@Transactional
	public Object insertEstimate(EstimateDTO estimateDTO) {
		log.info("[EstimateService] ===== insertEstimate Start =====");
		log.info("[EstimateService] estimateDTO : " + estimateDTO);
		int result = 0;
		try {	
			Estimate estimate = modelMapper.map(estimateDTO, Estimate.class);
			estimateRepository.save(estimate);
			result = 1;
		} catch (Exception e) {
			log.info("[estimate] exception");
		}
		log.info("[EstimateService] ====== insertEstimate End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}
	
	@Transactional
	public Object updateEstimate(EstimateDTO estimateDTO) {
		log.info("[EstimateService] ===== updateEstimate Start =====");
		int result = 0;
		try {
			Estimate estimate = estimateRepository.findById(estimateDTO.getEstimateCode()).get();
			estimate.setEstimateCode(estimateDTO.getEstimateCode());
			estimate.setEstimateStatus(estimateDTO.getEstimateStatus());
			estimate.setClient(clientRepository.findByClientCode(estimateDTO.getClient().getClientCode()));
			estimate.setEmp(empRepository.findByEmpCode(estimateDTO.getEmp().getEmpCode()));
			estimate.setStorage(storageRepository.findByStorageCode(estimateDTO.getStorage().getStorageCode()));
			estimate.setEstimateDetail(estimateDTO.getEstimateDetail());
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[EstimateService] ====== updateEstimate End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
	
	@Transactional
	public Object deleteEstimate(Integer estimateCode) {
	    log.info("[EstimateService] ===== deleteEstimate Start =====");
	    int result = 0;
	    try {
	        estimateRepository.deleteById(estimateCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[estimate delete] Exception!!");
	    }
	    log.info("[EstimateService] ====== deleteEstimate End ======");
	    return (result > 0) ? "견적서 삭제 성공" : "견적서 삭제 실패";
	}
		
	/* 주문 */
	public int selectOrdersTotal() {
		log.info("[OrdersService] selectOrdersTotal Start ==========");
		List<Orders> ordersList = ordersRepository.findAll();
		log.info("[OrdersService] selectOrdersTotal End ==========");
		return ordersList.size();
	}
	
	public Object selectOrdersListWithPaging(Criteria cri) {
		log.info("[OrdersService] selectOrdersListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("ordersCode").descending());
	    Page<Orders> result = ordersRepository.findAll(paging);
	    List<Orders> ordersList = (List<Orders>)result.getContent();
	    log.info("[OrdersService] selectOrdersListWithPaging End ============");
    return ordersList.stream().map(orders -> modelMapper.map(orders, OrdersDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectOrders(int ordersCode) {
		log.info("[OrdersService] selectOrders Start =====");
		Orders orders = ordersRepository.findById(ordersCode).get();
		log.info("[OrdersService] selectOrders End =======");
        OrdersDTO ordersDTO = modelMapper.map(orders, OrdersDTO.class);
		return ordersDTO;
	}
	
	@Transactional
	public Object insertOrders(OrdersDTO ordersDTO) {
		log.info("[OrdersService] ===== insertOrders Start =====");
		log.info("[OrdersService] ordersDTO : " + ordersDTO);
		int result = 0;
		try {	
			Orders orders = modelMapper.map(ordersDTO, Orders.class);
			ordersRepository.save(orders);
			result = 1;
		} catch (Exception e) {
			log.info("[orders] exception");
		}
		log.info("[OrdersService] ====== insertOrders End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}

	@Transactional
	public Object updateOrders(OrdersDTO ordersDTO) {
		log.info("[OrdersService] ===== updateOrders Start =====");
		int result = 0;
		try {
			Orders orders = ordersRepository.findById(ordersDTO.getOrdersCode()).get();
			orders.setOrdersCode(ordersDTO.getOrdersCode());
			orders.setOrdersDelivery(ordersDTO.getOrdersDelivery());
			orders.setOrdersStatus(ordersDTO.getOrdersStatus());
			orders.setClient(clientRepository.findByClientCode(ordersDTO.getClient().getClientCode()));
			orders.setEmp(empRepository.findByEmpCode(ordersDTO.getEmp().getEmpCode()));
			orders.setStorage(storageRepository.findByStorageCode(ordersDTO.getStorage().getStorageCode()));
			orders.setOrdersDetail(ordersDTO.getOrdersDetail());
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[OrdersService] ====== updateOrders End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
	
	@Transactional
	public Object deleteOrders(Integer ordersCode) {
	    log.info("[OrdersService] ===== deleteOrders Start =====");
	    int result = 0;
	    try {
	        ordersRepository.deleteById(ordersCode);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[orders delete] Exception!!");
	    }
	    log.info("[OrdersService] ====== deleteOrders End ======");
	    return (result > 0) ? "주문서 삭제 성공" : "주문서 삭제 실패";
	}
	
	/* 판매 */
	public int selectSalesTotal() {
		log.info("[SalesService] selectSalesTotal Start ==========");
		List<Sales> salesList = salesRepository.findAll();
		log.info("[SalesService] selectSalesTotal End ==========");
		return salesList.size();
	}
	
	public Object selectSalesListWithPaging(Criteria cri) {
		log.info("[SalesService] selectSalesListWithPaging Start ==========");
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("salesCode").descending());
	    Page<Sales> result = salesRepository.findAll(paging);
	    List<Sales> salesList = (List<Sales>)result.getContent();
	    log.info("[SalesService] selectSalesListWithPaging End ============");
    return salesList.stream().map(sales -> modelMapper.map(sales, SalesDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectSales(int salesCode) {
		log.info("[SalesService] selectSales Start =====");
		Sales sales = salesRepository.findById(salesCode).get();
		log.info("[SalesService] selectSales End =======");
        SalesDTO salesDTO = modelMapper.map(sales, SalesDTO.class);
		return salesDTO;
	}

	@Transactional
	public Object insertSales(SalesDTO salesDTO) {
	    log.info("[SalesService] ===== insertSales Start =====");
	    log.info("[SalesService] salesDTO : " + salesDTO);
	    int result = 0;
	    try {   
	        Sales sales = new Sales();
	        sales.setSalesCode(salesDTO.getSalesCode());
	        sales.setSalesDate(salesDTO.getSalesDate());
	        sales.setClient(clientRepository.findByClientCode(salesDTO.getClient().getClientCode()));
	        sales.setEmp(empRepository.findByEmpCode(salesDTO.getEmp().getEmpCode()));
	        
	        List<SalesDetail> salesDetailList = new ArrayList<>();
	        for(SalesDetail salesDetailDTO : salesDTO.getSalesDetail()) {
	            SalesDetail salesDetail = new SalesDetail();
	            salesDetail.setSalesNo(salesDetailDTO.getSalesNo());
	            salesDetail.setStock(stockRepository.findByStockCode(salesDetailDTO.getStock().getStockCode()));
	            salesDetail.setSalesAmount(salesDetailDTO.getSalesAmount());
	            salesDetail.setSalesNote(salesDetailDTO.getSalesNote());
	            salesDetailList.add(salesDetail);
	            
	            Stock stock = stockRepository.findByStockCode(salesDetailDTO.getStock().getStockCode());
	            stock.setStockAmount(stock.getStockAmount() - salesDetailDTO.getSalesAmount());
	            stockRepository.save(stock);
	            
	        }
	        sales.setSalesDetail(salesDetailList);
	        salesRepository.save(sales);
	        result = 1;
	    } catch (Exception e) {
	        log.info("[sales] exception");
	    }
	    log.info("[SalesService] ====== insertSales End ======");
	    return (result > 0)? "판매 등록 성공" : "판매 등록 실패";
	}
	
	@Transactional
	public Object updateSales(SalesDTO salesDTO) {
		log.info("[SalesService] ===== updateSales Start =====");
		int result = 0;
		try {   
	        Sales sales = new Sales();
	        sales.setSalesCode(salesDTO.getSalesCode());
	        sales.setSalesDate(salesDTO.getSalesDate());
	        sales.setClient(clientRepository.findByClientCode(salesDTO.getClient().getClientCode()));
	        sales.setEmp(empRepository.findByEmpCode(salesDTO.getEmp().getEmpCode()));
	        
	        List<SalesDetail> salesDetailList = new ArrayList<>();
	        for(SalesDetail salesDetailDTO : salesDTO.getSalesDetail()) {
	            SalesDetail salesDetail = new SalesDetail();
	            salesDetail.setSalesNo(salesDetailDTO.getSalesNo());
	            salesDetail.setStock(stockRepository.findByStockCode(salesDetailDTO.getStock().getStockCode()));
	            salesDetail.setSalesAmount(salesDetailDTO.getSalesAmount());
	            salesDetail.setSalesNote(salesDetailDTO.getSalesNote());
	            salesDetailList.add(salesDetail);
	            
	            Stock stock = stockRepository.findByStockCode(salesDetailDTO.getStock().getStockCode());
	            stock.setStockAmount(stock.getStockAmount() - salesDetailDTO.getSalesAmount());
	            stockRepository.save(stock);
	            
	        }
	        sales.setSalesDetail(salesDetailList);
	        salesRepository.save(sales);
	        result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
		}
		log.info("[SalesService] ====== updateSales End ======");
		return (result > 0) ? "창고 수정 성공" : "창고 수정 실패" ;
	}
	
}
