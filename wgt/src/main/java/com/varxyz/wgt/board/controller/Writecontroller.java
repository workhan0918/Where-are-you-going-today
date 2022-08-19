package com.varxyz.wgt.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.wgt.board.domain.Board;
import com.varxyz.wgt.board.service.BoardService;
import com.varxyz.wgt.board.service.BoardServiceImpl;

@Controller
public class Writecontroller {
	BoardService service = new BoardServiceImpl();

	// 등록하기 화면
	@GetMapping("/board/write")
	public String post(Model model) {
		return "/board/write";
	}

	@PostMapping("/board/write")
	public String post(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model, HttpSession session) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일사이즈
		Board board = new Board();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uploadFolder = "C:\\NCS\\Where-are-you-going-today\\wgt\\src\\main\\webapp\\resources\\board\\img\\upload";

		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");

		String uniqueName = uuids[0];
		System.out.println("생성된 고유 문자열 : " + uniqueName );
		board.setImgname(uniqueName);
		System.out.println("확장자명 : " + fileExtension);
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); // 적용 후
		
		try {
			file.transferTo(saveFile); // 실제 파일 저장메소드(filewriter 작업을 손쉽게 한방에 처리해준다.
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("용량 초과로 인한 오류");
		}
		
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));

		service.create(board, uniqueName);
		model.addAttribute(formatter);
		model.addAttribute("msg", "게시글 작성을 완료하였습니다.");
		model.addAttribute("url","home"); //alert model.addAttribute 할땐 msg랑 url 둘 다

		return "alert/alert";
		}

}
