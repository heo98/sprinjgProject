package kr.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ezen.bbs.domain.BoardDTO;
import kr.ezen.bbs.domain.PageDTO;
import kr.ezen.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list.do")
	/*
	 * public String list(Model model) { List<BoardDTO> list=service.getList();
	 * model.addAttribute("list",list);
	 * 
	 * return "board/boardList"; }
	 */
	public String list(PageDTO pDto, Model model) {
		List<BoardDTO> list=service.getList(pDto);
		model.addAttribute("list",list);
		model.addAttribute("pDto", pDto);
		
		return "board/boardList"; 
	}
	
	@GetMapping("/register.do")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("/register.do")
	public String register(BoardDTO dto) {
//		for (int i = 1; i <= 113; i++) {
//	         BoardDTO bDto = new BoardDTO();
//	         bDto.setSubject(i + "번째 제목");
//	         bDto.setContents(i + "번째 내용");
//	         bDto.setWriter((i % 10) + "번쨰 글쓴이");
//	         service.register(bDto);
//	      }
		service.register(dto);
		
		return "redirect:/board/list.do";
	}
	
	@GetMapping("/view.do")
	public String view(int bid,@ModelAttribute("pDto") PageDTO pDto, Model m) {
		BoardDTO dto=service.view(bid, "v");
		m.addAttribute("dto", dto);
		
		return "board/view";
	}
		
	//수정 폼페이지
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyForm(int bid, Model m) {
		BoardDTO dto=service.view(bid, "m");
		m.addAttribute("dto", dto);
		
		return "board/modify";
	}
	
	//게시글 수정
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(BoardDTO dto) {
		service.modify(dto);
		return "redirect:list.do";
	}
	
	//게시글 삭제
	@GetMapping("/remove.do")
	public String remove(int bid) {
		service.remove(bid);
		
		return "redirect:list.do";
	}
}
