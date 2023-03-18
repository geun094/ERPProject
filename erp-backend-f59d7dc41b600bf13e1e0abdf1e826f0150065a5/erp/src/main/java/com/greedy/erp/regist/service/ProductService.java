package com.greedy.erp.regist.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.ProductDTO;
import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.repository.ProductRepository;
import com.greedy.erp.util.FileUploadUtils;

@Service
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소(WebConfig 설정파일 추가하기) */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
    
	@Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	

	/* 상품 전체 조회 */
	public int selectProductTotal() {
		
		log.info("[ProductService] selectProductTotal Start ===================================");
        
        /* 페이징 처리 결과를 Page 타입으로 반환받음 */
        List<Product> productList = productRepository.findAll();
        
        log.info("[ProductService] selectProductTotal End ===================================");
        
        return productList.size();
	}

	/* 전체 조회 페이징 */
	public Object selectProductListWithPaging(Criteria cri) {

	log.info("[ProductService] selectProductListWithPaging Start ===================================");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("productCode").descending());
	        
        Page<Product> result = productRepository.findAll(paging);
        List<Product> productList = (List<Product>)result.getContent();

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + "productimgs/" + productList.get(i).getProductImageUrl());
        }
        
        log.info("[ProductService] selectProductListWithPaging End ===================================");
        
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}
		
	
	/* 상품 등록 */
	@Transactional
	public Object insertProduct(ProductDTO productDTO, MultipartFile productImage) {
        log.info("[ProductService] insertProduct Start ===================================");
        log.info("[ProductService] productDTO : " + productDTO);
        
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;

        try {
        	if(productImage != null) {
        		
        		replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR + "productimgs", imageName, productImage);
        		
        		productDTO.setProductImageUrl(replaceFileName);
        		
        		log.info("[ProductService] insert Image Name : ", replaceFileName);        			
        	}
        	
            Product insertProduct = modelMapper.map(productDTO, Product.class);
            
            productRepository.save(insertProduct);
            
            result = 1;
        } catch (Exception e) {
//        	log.info("[product insert] exception!!");
        	FileUploadUtils.deleteFile(IMAGE_DIR + "productimgs", replaceFileName);
            throw new RuntimeException(e);
        }
        
        log.info("[ProductService] insertProduct End ===================================");
        
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
	}


	/* 상품 수정 */
	@Transactional
	public Object updateProduct(ProductDTO productDTO, MultipartFile productImage) {
		log.info("[ProductService] updateProduct Start ===================================");
        log.info("[ProductService] productDTO : " + productDTO);
        
        String replaceFileName = null;
        int result = 0;

        try {
        	
        	/* update 할 엔티티 조회 */
        	Product product = productRepository.findById(productDTO.getProductCode()).get();
        	String oriImage = product.getProductImageUrl();
        	log.info("[ProductService] productDTO : " + productDTO);
        	
        	/* 수정 가능한 속성 값 설정*/
            product.setProductName(productDTO.getProductName());
            product.setProductRcvPrice(productDTO.getProductRcvPrice());
            product.setProductFwdPriceA(productDTO.getProductFwdPriceA());
            product.setProductFwdPriceB(productDTO.getProductFwdPriceB());
            product.setProductFwdPriceC(productDTO.getProductFwdPriceC());
            product.setProductType(productDTO.getProductType());
            

            if(productImage != null){  //이미지 파일이 추가/수정 된다면 
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR + "productimgs", imageName, productImage);
                log.info("[updateProduct] InsertFileName : " + replaceFileName);
                
                product.setProductImageUrl(replaceFileName);	// 새로운 파일 이름으로 update
                log.info("[updateProduct] deleteImage : " + oriImage);
				if (oriImage != null) { // 원래 이미지 파일이 있었다면
					boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR + "productimgs", oriImage);
					log.info("[update] isDelete : " + isDelete);
				}
            } else {
            	
                /* 이미지 변경 없을 시 */
            	product.setProductImageUrl(oriImage);
            }

            result = 1;
            
        }  catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
	}


	/* 상품 삭제 */
	@Transactional
	public Object deleteProduct(int productCode) {
		int result = 0;
		try {
			productRepository.deleteById(productCode);
			result = 1;
		} catch(Exception e) {
			 log.info("[product delete] Exception!!");
		}
		
		return (result > 0) ? "상품 삭제 성공" : "상품 삭제 실패";
	}
	
	/* 상세 조회*/
	public Object selectProduct(int productCode) {
		log.info("[ProductService] selectProduct Start =====");
		
		Product product = productRepository.findById(productCode).get();
		product.setProductImageUrl(IMAGE_URL + "productimgs/" + product.getProductImageUrl());
		
		log.info("[ProductService] selectProduct End =======");
		
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        
		return productDTO;
	}

	/* 검색 조회 */
	public Object selectSearchProductList(String search) {
		log.info("[ProductService] selectSearchProductList Start ===================================");
        log.info("[ProductService] searchValue : " + search);
        
        List<Product> productListSearch = productRepository.findByProductNameContaining(search);
        
        
        log.info("[ProductService] selectSearchProductList End ===================================");

        return productListSearch.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	
}
