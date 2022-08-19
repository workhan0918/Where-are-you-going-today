package com.varxyz.wgt.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.service.BoardService;
import com.varxyz.wgt.board.service.BoardServiceImpl;
import com.varxyz.wgt.user.domain.User;
import com.varxyz.wgt.user.service.UserService;
import com.varxyz.wgt.user.serviceImpl.UserServiceImpl;

@Controller
public class MypageController {
	BoardService service = new BoardServiceImpl();
	UserService userService = new UserServiceImpl();

	// 회원정보 가져오기
	@GetMapping("/board/mypage")
	public String post(HttpSession session, Model model, Board board) {
		List<User> userList = userService.inquiryUser((String) session.getAttribute("userId"));
//		System.out.println(session.getAttribute("userId"));
		model.addAttribute("userList", userList);
		model.addAttribute("board", service.read(board));
		session.setAttribute("number", board.getNumber());
		return "/board/mypage";
	}

//	@PostMapping("/board/mypage")
//	public String post(Board board, Model model) {
//		model.addAttribute("Board", board);
//		service.create(board, "imgname");
//		model.addAttribute("msg", "게시글 수정을 완료하였습니다.");
//		model.addAttribute("url", "home");
//		return "alert/alert";
//	}

	//게시글 수정
	@GetMapping("/board/update")
	public String updateget(MultipartFile file, HttpServletRequest request, HttpSession session, Model model) {
		Board board = new Board();
		String bidboard = (String)session.getAttribute("imgname");
		board.setImgname(bidboard);
		model.addAttribute("board", service.read(board));
		session.setAttribute("board", board);
		return "board/update";
	}
	
	@PostMapping("/board/update")
	public String update(@RequestParam("file") MultipartFile file,@RequestParam("bid") int bid, HttpServletRequest request, Board board, Model model) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize();
				
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uploadFolder = "C:\\NCS\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload";

		UUID uuid = UUID.randomUUID();
		String[] uuids = uuid.toString().split("-");
		String uniqueName = uuids[0];
		
		System.out.println(uuid.toString());
		System.out.println("생성된 고유 문자열 : " + uniqueName );
		System.out.println("확장자명 : " + fileExtension);
		
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후

		try {
			file.transferTo(saveFile); //파일 저장메소드
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		board.setImgname(uniqueName);
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setImgname(request.getParameter("imgname"));
		
		service.update(board, uniqueName);
		
		model.addAttribute("msg", "게시글 수정을 완료하였습니다.");
		model.addAttribute("url","mypage");
		
		return "/board/mypage";
	}
	
	// 게시글 삭제
	@GetMapping("/board/delete")
	public String deleteGet(@RequestParam("bid") int bid, Model model, HttpSession session, Board board) {
		Board bidboard = new Board();
		bidboard = service.searchByBid(bid);
		String imgname = bidboard.getImgname(); //bidboard 선언, imgname-board 객체변환
		
		String filePath = "C:\\NCS\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload\\" + imgname + ".jpg";
        File file = new File(filePath);
        System.out.println(bid);
        System.out.println(file);
        System.out.println(service.searchByBid(bid));
        // 파일이 존재하는지 체크 존재할경우 true, 존재하지않을경우 false
        if(file.exists()) {
            // 파일을 삭제합니다.
        	session.removeAttribute(imgname);
            file.delete(); 
            System.out.println("파일을 삭제하였습니다.");
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
		service.delete(bid, imgname);
		return "redirect:/board/mypage";
	}

}