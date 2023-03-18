package com.greedy.erp.board.service;

import java.util.List;
import java.util.UUID;
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
import com.greedy.erp.board.dto.BoardDTO;
import com.greedy.erp.board.dto.TodoDTO;
import com.greedy.erp.board.entity.Board;
import com.greedy.erp.board.entity.Todo;
import com.greedy.erp.board.repository.TodoRepository;
import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Position;
import com.greedy.erp.util.FileUploadUtils;

@Service
public class TodoService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);
	private final TodoRepository todoRepository;
	private final ModelMapper modelMapper;
	private final EmpRepository empRepository;
	
	@Autowired
	public TodoService( TodoRepository todoRepository, ModelMapper modelMapper,EmpRepository empRepository) {
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
	}
	

	/* 내 업무 조회 */
	public int selectTodoTotal(int empCode) {
		log.info("[TodoService] selectToDoTotal Start ==========");
		Emp emp = empRepository.findById(empCode).get();
		List<Todo> TodoList = todoRepository.findByEmpCode(empCode);
		log.info("[TodoService] selectToDoTotal End ==========");
		
		return TodoList.size();
	}
	


	public Object selectTodoListWithPaging(Criteria cri, int empCode) {
		log.info("[TodoService] selectTodoListWithPaging Start ==========");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);
	        
        List<Todo> TodoList = todoRepository.findByEmpCode(empCode,paging);
        log.info("[TodoService] selectTodoListWithPaging End ============");
        return TodoList.stream().map(todo -> modelMapper.map(todo, TodoDTO.class)).collect(Collectors.toList());
	}

	
	
	
	/* 내 업무 상세 조회 */
	public Object selectTodo(int todoCode) {
		log.info("[TodoService] selectBoard Start ==========");
		
		Todo todo = todoRepository.findById(todoCode).get();
		
		log.info("[TodoService] selectBoard End ==========");
		
		TodoDTO todoDTO = modelMapper.map(todo,TodoDTO.class);
		
		return todoDTO;
	}



	/* 등록 */
	public Object insertTodo(TodoDTO todoDTO, int empCode) {
		log.info("[TodoService] ===== insertTodo Start =====");
		log.info("[TodoService] todoDTO : " + todoDTO);
		Emp emp = empRepository.findById(empCode).get();
        
		int result = 0;
		try {	
			Todo insertTodo = modelMapper.map(todoDTO, Todo.class);
			insertTodo.setEmp(emp);
			todoRepository.save(insertTodo);
            
			result = 1;
		} catch (Exception e) {
			log.info("[todo] exception");
		}
		log.info("[TodoService] ====== insertTodo End ======");
		return (result > 0)? "등록 성공" : "등록 실패";
		}


	/* 수정 */
	@Transactional
	public Object updateTodo(TodoDTO todoDTO) {
		log.info("[TodoService] ===== updateTodo Start =====");
		log.info("[TodoService] todoDTO : " + todoDTO);
		
		int result = 0;
		try {
			Todo todo = todoRepository.findById(todoDTO.getTodoCode()).get();
            /* update를 위한 엔티티 값 수정 */
			todo.setTodoCode(todoDTO.getTodoCode());
			todo.setTodoContent(todoDTO.getTodoContent());
			todo.setCompleteYn(todoDTO.getCompleteYn());
			todo.setTodoDate(todoDTO.getTodoDate());
			todo.setTodoDeadline(todoDTO.getTodoDeadline());
			
			result = 1;
		} catch (Exception e) {
			log.info("[review update] Exception!!");
            throw new RuntimeException(e);
		}
		log.info("[TodoService] ====== updateTodo End ======");
		return (result > 0) ? todoDTO : "업무 수정 실패" ;
	}
	
	/*검색*/
	public Object searchTodoByComplete(String search) {
		log.info("[TodoService] searchTodoByComplete Start =====");
		
 
		List<Todo> searchedTodos = todoRepository.findByCompleteYn(search);
		log.info("[TodoService] searchTodoByComplete End =======");
		return searchedTodos.stream().map(todo -> modelMapper.map(todo, TodoDTO.class)).collect(Collectors.toList());
	
	}
}
