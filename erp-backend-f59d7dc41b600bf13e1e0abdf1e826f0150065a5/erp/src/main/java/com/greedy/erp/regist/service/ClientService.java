package com.greedy.erp.regist.service;

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

import com.greedy.erp.common.Criteria;
import com.greedy.erp.regist.dto.ClientDTO;
import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.repository.ClientRepository;


@Service
public class ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(StorageService.class);
	private final ClientRepository clientRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
	}
	
		public int selectClientTotal() {
			log.info("[ClientService] selectClientTotal Start ==========");
			List<Client> clientList = clientRepository.findAll();
			log.info("[ClientService] selectClientTotal End ==========");
			
			return clientList.size();
		}
		
		public Object selectClientListWithPaging(Criteria cri) {
			log.info("[ClientService] selectClientListWithPaging Start ==========");
			
			int index = cri.getPageNum() - 1;
		    int count = cri.getAmount(); 
		    Pageable paging = PageRequest.of(index, count, Sort.by("clientCode").descending());
		        
		    Page<Client> result = clientRepository.findAll(paging);
		    List<Client> clientList = (List<Client>)result.getContent();
		    
		    log.info("[ClientService] selectClientListWithPaging End ============");
	    
	    return clientList.stream().map(client -> modelMapper.map(client, ClientDTO.class)).collect(Collectors.toList());
		}
		
		public Object selectClient(int clientCode) {
			log.info("[ClientService] selectClient Start =====");
			
			Client client = clientRepository.findById(clientCode).get();
			
			log.info("[ClientService] selectClient End =======");
			
	        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
	        
			return clientDTO;
		}
		
		public Object searchClientByType(String search) {
			
			log.info("[ClientService] searchClientByType Start =====");
			
			List<Client> searchByType = clientRepository.findByClientType(search);
			
			log.info("[ClientService] searchClientByType End =======");
			
			return searchByType.stream().map(client -> modelMapper.map(client, ClientDTO.class)).collect(Collectors.toList());
		
		}	
		@Transactional
		public Object insertClient(ClientDTO clientDTO) {
			log.info("[ClientService] ===== insertClient Start =====");
			log.info("[ClientService] clientDTO : " + clientDTO);
	        
			int result = 0;
			try {	
				Client client = modelMapper.map(clientDTO, Client.class); // 한번에 전체 등록 매핑
				
//				Client client = new Client();							  // 부분적 등록을 할 때 사용할 것
//				client.setClientCode(clientDTO.getClientCode());
//				client.setClientName(clientDTO.getClientName());
//				client.setClientRepresentative(clientDTO.getClientRepresentative());
//				client.setClientType(clientDTO.getClientType());
//				client.setClientItem(clientDTO.getClientItem());
//				client.setClientPhone(clientDTO.getClientPhone());
//				client.setClientMobile(clientDTO.getClientMobile());
//				client.setClientFax(clientDTO.getClientFax());
//				client.setClientAddress(clientDTO.getClientAddress());
//				client.setClientAccount(clientDTO.getClientAccount());
//				client.setClientWeb(clientDTO.getClientWeb());
//				client.setClientEmail(clientDTO.getClientEmail());
				
				clientRepository.save(client);
				
				result = 1;
			} catch (Exception e) {
				log.info("[client] exception");
			}
			
			log.info("[ClientService] ====== insertClient End ======");
			return (result > 0)? "등록 성공" : "등록 실패";
			}

		@Transactional
		public Object updateClient(ClientDTO clientDTO) {
			log.info("[ClientService] ===== updateClient Start =====");
			
			int result = 0;
			
			try {
				Client client = clientRepository.findById(clientDTO.getClientCode()).get();
				client.setClientName(clientDTO.getClientName());
				client.setClientType(clientDTO.getClientType());
				
				result = 1;
			} catch (Exception e) {
				log.info("[review update] Exception!!");
			}
			
			log.info("[ClientService] ====== updateClient End ======");
			return (result > 0) ? "거래처 수정 성공" : "거래처 수정 실패" ;
		}
		
		@Transactional
		public Object deleteClient(int clientCode) {
		    log.info("[ClientService] ===== deleteClient Start =====");
		    
		    int result = 0;
		    
		    try {
		        clientRepository.deleteById(clientCode);
		        result = 1;
		    } catch (Exception e) {
		        log.info("[client delete] Exception!!");
		    }
		    
		    log.info("[ClientService] ====== deleteClient End ======");
		    return (result > 0) ? "거래처 삭제 성공" : "거래처 삭제 실패";
		};
		
		@Transactional
		public Object deleteClients(List<Integer> clientCodes) {
		    clientRepository.deleteAllInBatch(clientRepository.findAllById(clientCodes));
		    return clientCodes;
		}

	};