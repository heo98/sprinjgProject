package kr.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.bbs.domain.BoardDTO;
import kr.ezen.bbs.domain.PageDTO;
import kr.ezen.bbs.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Override
	public int totalCnt() {
		return mapper.totalCnt();
	}

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void register(BoardDTO dto) {
		
		mapper.insert(dto);
	}
	
	@Override
	/*
	 * public List<BoardDTO> getList(){ return mapper.getList(); }
	 */
	public List<BoardDTO> getList(PageDTO pDto){
		int totalCnt=mapper.totalCnt();
		pDto.setValue(totalCnt, pDto.getCntPerPage());
		
		return mapper.getList(pDto);
	}
	
	@Override
//	 public BoardDTO view(int bid) {
	 public BoardDTO view(int bid, String mode) {
		if(mode.equals("v")) {
			
			//조회수 증가
			mapper.hitAdd(bid);
		}
		
		return mapper.view(bid);
	}
	
	@Override
	public int modify(BoardDTO dto) {
		return mapper.update(dto);
	}
	
	@Override
	public int remove(int bid) {
		return mapper.delete(bid);
	}

}
