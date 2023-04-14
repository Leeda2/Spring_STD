package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //게시글 작성
    @GetMapping("/board/write")

    public String boardWriteForm(){

        return "boardwrite";
    }


    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{
//        System.out.println("제목 :"+ board.getTitle());
//        System.out.println("제목 :"+ board.getContent());

        boardService.write(board,file);

        model.addAttribute("message","글작성이 완료 되었습니다.");
        model.addAttribute("searchUrl","/board/list");


        return "message";

    }

    //게시글 리스트
    @GetMapping("/board/list")
    public String boardList(Model model){
        //Model :데이터를 담아서 보내줌
        model.addAttribute("list",boardService.boardList());
        //boardservice에 만든 list를 받아서 넘기겠다요! list라는 이름으로 반환

         log.info("list_ok");
         log.info(model.addAttribute("list",boardService.boardList()));
        return "boardlist";

    }

    //게시글 view
    @GetMapping("/board/view") // localhost:8080/board/view?id=1
    public String boardView(Model model ,Integer id){

        model.addAttribute("board",boardService.boardView(id));

        return "boardview";
    }

    //게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Integer id,Model model){
        boardService.boardDelete(id);

        model.addAttribute("message","글삭제가 완료 되었습니다.");
        model.addAttribute("searchUrl","/board/list");


        return "message";
    }

    //게시글 수정이동
    @GetMapping("/board/modify/{id}")
    //{id} 처럼 url에서 url에서 각 구분자에 들어오는 값을 처리해야 할 때 사용합니다.
    //url 구분자와 @PathVariable 값 동일하게 사용
    public String boardModify(@PathVariable("id") Integer id,Model model){

        model.addAttribute("board",boardService.boardView(id));

        return "boardmodify";
    }
    //게시글 수정
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board,Model model,MultipartFile file) throws Exception{

        // 원래값이 아닌 수정된 값을 저장해야 한기 때문에 temp에 담는다.
        Board boardTemp=boardService.boardView(id);
        // 수정된 내용을 덮어 씌운다.
        boardTemp.setTitle(board.getTitle());
        boardTemp.setTitle(board.getContent());

        boardService.write(board,file);


        model.addAttribute("message","글수정이 완료 되었습니다.");
        model.addAttribute("searchUrl","/board/list");


        return "message";

//        return "redirect:/board/list";
    }



}
