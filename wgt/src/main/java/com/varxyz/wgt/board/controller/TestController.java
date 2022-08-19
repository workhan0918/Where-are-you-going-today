//package com.varxyz.wgt.board.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.varxyz.wgt.board.domain.Board;
//import com.varxyz.wgt.board.service.BoardService;
//import com.varxyz.wgt.board.service.BoardServiceImpl;
//import com.varxyz.wgt.user.domain.User;
//import com.varxyz.wgt.user.service.UserService;
//import com.varxyz.wgt.user.serviceImpl.UserServiceImpl;
//
//@Controller
//public class TestController {
//	BoardService service = new BoardServiceImpl();
//	UserService userService = new UserServiceImpl();
//	
//	// 회원정보 가져오기
//	@GetMapping("/board/test")
//	public String postForm(HttpSession session, Model model, Board board) {
//		List<User> userList = userService.inquiryUser((String)session.getAttribute("userId"));
////		System.out.println(session.getAttribute("userId"));
//		model.addAttribute("userList", userList);
//		model.addAttribute("board", service.read(board));
//		return "/board/test";
//	}
//	
//	@PostMapping("/board/test")
//	public String post(Board board, Model model) {
//		model.addAttribute("Board", board);
//		service.create(board,"imgName");
//		model.addAttribute("msg", "게시글 수정을 완료하였습니다.");
//		model.addAttribute("url","test");
//		return "alert/alert";
//	}
//	
//	//게시글 삭제
//	@GetMapping("/board/delete")
//	public String deleteGet(@RequestParam("bid") int bid, Model model) {
//		System.out.println(bid);
//		service.delete(bid);
//		model.addAttribute("msg", "게시글 삭제를 완료하였습니다.");
//		model.addAttribute("url","test");
//		return "alert/alert";
//	}
//	
////	@PostMapping("/board/delete")
////	public String delete(Board board, Model model) {
////		service.delete(board.getNumber()); 
////		List<Board> list = service.search(board.getTitle());
////		model.addAttribute("list", list);
////		model.addAttribute("msg", "게시글 삭제를 완료하였습니다.");
////		model.addAttribute("url","test");
////		return "alert/alert";
////	}
//
//}