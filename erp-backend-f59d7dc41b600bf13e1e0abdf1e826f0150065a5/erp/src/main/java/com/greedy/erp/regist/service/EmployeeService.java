package com.greedy.erp.regist.service;

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
import com.greedy.erp.regist.dto.EmpDTO;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.repository.EmployeeRepository;
import com.greedy.erp.util.FileUploadUtils;

@Service
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	private final ModelMapper modelMapper;
	private final EmployeeRepository employeeRepository;
	
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
    
	@Autowired
	public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
		this.modelMapper = modelMapper;
		this.employeeRepository = employeeRepository;
	}
	
	// 로그인한 사원 1명 정보 조회하기
	public Object findEmpByEmpCode(Integer empCode) {
		Emp emp = employeeRepository.findEmpByEmpCode(empCode);
		emp.setEmpImage(IMAGE_URL + "profileimgs/" + emp.getEmpImage());
		emp.setEmpStamp(IMAGE_URL + "stampimgs/" + emp.getEmpStamp());
		return modelMapper.map(emp, EmpDTO.class);
	} 
	
	// 전체 사원 조회하기
	public List<EmpDTO> selectAllEmp() {
		log.info("[EmployeeService] selectAllEmployee Start ==========");
		
		List<Emp> empList = employeeRepository.findAll();
		
		log.info("[EmployeeService] selectStorageTotal End ==========");
		
		return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).collect(Collectors.toList());
		
	}
	
	public Object selectEmployeeListWithPaging(Criteria cri) {
		log.info("[EmployeeService] selectEmployeeListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
	    int count = cri.getAmount(); 
	    Pageable paging = PageRequest.of(index, count, Sort.by("empCode").descending());
	    log.info("고양이");
	    Page<Emp> result = employeeRepository.findAll(paging);
	    log.info("고양이2");
	    List<Emp> empList = (List<Emp>)result.getContent();
	    log.info("고양이3");
	    
	    log.info("[StorageService] selectStorageListWithPaging End ============");
    
    return empList.stream().map(emp -> modelMapper.map(emp, EmpDTO.class)).collect(Collectors.toList());
	}
	
	public int selectEmployeeTotal() {
		log.info("[EmployeeService] selectEmployeeTotal Start ==========");
		List<Emp> empList = employeeRepository.findAll();
		log.info("[EmployeeService] selectEmployeeTotal End ==========");
		
		return empList.size();
	}

	/* 사원 등록 */
	@Transactional
	public Object insertEmployee(EmpDTO empDTO) {
		log.info("[EmployeeService] ===== insertEmployee Start =====");
		log.info("[EmployeeService] EmpDTO : " + empDTO);
        
		int result = 0;
		try {	
			Emp emp = modelMapper.map(empDTO, Emp.class); 
			
			employeeRepository.save(emp);
			
			result = 1;
		} catch (Exception e) {
			log.info("[EmployeeService] exception");
		}
		
		log.info("[EmployeeService] ====== insertEmployee End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}

	/* 사원 수정 */
	@Transactional
	public Object modifyEmployee(EmpDTO empDTO) {
		log.info("[EmployeeService] ===== modifyEmployee Start =====");
		log.info("[EmployeeService] EmpDTO : " + empDTO);
        
		int result = 0;
		try {	
			Emp emp = modelMapper.map(empDTO, Emp.class); 
			
			employeeRepository.save(emp);
			
			result = 1;
		} catch (Exception e) {
			log.info("[EmployeeService] exception");
		}
		
		log.info("[EmployeeService] ====== modifyEmployee End ======");
		return (result > 0)? "수정 성공" : "수정 실패";
		}

	/* 테마 색상 수정 */
    @Transactional
	public Object updateTheme(int empCode, String color) {
		Emp emp = employeeRepository.findById(empCode).get();
		emp.setEmpTheme(color);
		return emp;
	}

	/* 도장 수정 */
	@Transactional
	public Object updateStamp(int empCode, MultipartFile stampImage) {
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        Emp emp = employeeRepository.findById(empCode).get();  
        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR + "stampimgs", imageName, stampImage);
            emp.setEmpStamp(replaceFileName);    
            employeeRepository.save(emp);
            result = 1;
        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR + "stampimgs", replaceFileName);
            throw new RuntimeException(e);
        }
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
	}

	public Object updateEmpImg(int empCode, MultipartFile employeeImg) {
		 String imageName = UUID.randomUUID().toString().replace("-", "");
	        String replaceFileName = null;
	        int result = 0;
	        Emp emp = employeeRepository.findById(empCode).get();  
	        try {
	            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR + "profileimgs", imageName, employeeImg);
	            emp.setEmpImage(replaceFileName);    
	            employeeRepository.save(emp);
	            result = 1;
	        } catch (Exception e) {
	            FileUploadUtils.deleteFile(IMAGE_DIR + "profileimgs", replaceFileName);
	            throw new RuntimeException(e);
	        }
	        return (result > 0) ? "사원 사진 수정 성공" : "사원 사진 수정 실패";
	}
}
