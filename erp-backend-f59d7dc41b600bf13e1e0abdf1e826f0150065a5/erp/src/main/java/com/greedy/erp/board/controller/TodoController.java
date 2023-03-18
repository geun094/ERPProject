package com.greedy.erp.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.erp.board.dto.TodoDTO;
import com.greedy.erp.board.service.TodoService;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.common.PageDTO;
import com.greedy.erp.common.PagingResponseDTO;
import com.greedy.erp.common.ResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@CrossOrigin(origins = "http://localhost:7777")
@RequestMapping("/api/v1")
public class TodoController {
	

private static final Logger log = LoggerFactory.getLogger(TodoController.class);
	
	private final TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;	
	}
	
	/* 사원코드기준으로  업무리스트 조회 */
	@Operation(summary = "업무 조회", description = "업무 조회 및 페이지", tags = { "TodoController" })
	@GetMapping("/todos/{empCode}")
	public ResponseEntity<ResponseDTO> selectTodo(@PathVariable int empCode, @RequestParam(name = "offset", defaultValue = "1") String offset) {
		log.info("[TodoController] selectTodo : " + offset);
		
		int total = todoService.selectTodoTotal(empCode);
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		pagingResponseDTO.setData(todoService.selectTodoListWithPaging(cri,empCode));	
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업무 조회 성공", pagingResponseDTO));	
	}
	
	/* 업무 상세조회*/
	@Operation(summary = "업무 상세 요청", description = "업무 상세 조회", tags = { "TodoController" })
    @GetMapping("/myTodos/{todoCode}")
    public ResponseEntity<ResponseDTO> detailBoard(@PathVariable int todoCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업무 상세 조회 성공",  todoService.selectTodo(todoCode)));
    }
	
	
	/* 등록*/
	@Operation(summary = "업무 등록 요청", description = "해당 업무 등록이 진행됩니다.", tags = { "TodoController" })
    @PostMapping(value = "/todos/{empCode}")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute TodoDTO todoDTO,@PathVariable int empCode) {
    	
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업무 입력 성공",  todoService.insertTodo(todoDTO, empCode)));
    }
	
	/* 업무 수정 */
	@Operation(summary = "업무 수정", description = "업무 수정 진행", tags = { "TodoController" })
    @PutMapping("/todos/{empCode}")
	
    public ResponseEntity<ResponseDTO> updateTodo(@ModelAttribute TodoDTO todoDTO ) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업무 수정 성공", todoService.updateTodo(todoDTO)));
    }

	/* 검색 */
	@Operation(summary = "업무 검색", description = "업무 검색 진행", tags = { "TodoController" })
	@GetMapping("/todo/search")
    public ResponseEntity<ResponseDTO> searchTodoByComplete(@RequestParam(name="s", defaultValue="all") String search) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업무 검색 성공",  todoService.searchTodoByComplete(search)));
    }
	

}
