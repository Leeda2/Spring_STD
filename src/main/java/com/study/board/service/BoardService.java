package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    // 알아서 객체를 생성해주도록 하는게 autowired
    private BoardRepository boardRepository;


    //글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();
        //UUId : 식별자
        //random: 랜덤으로 식별자를 만들어줌

        String fileName = uuid + "_" + file.getOriginalFilename();
        //fileName 에 식별자_기존파일 이름으로 저장
        File saveFile = new File(projectPath, fileName);
        //projectpath의 경로에 파일을 넣고 이름은 name으로 넣겠다.


        file.transferTo(saveFile);


        //file 경로 데이터 넣기
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);


        boardRepository.save(board);
    }
    // service는 컨트롤러 에서 이용


    //게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();


   }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        // 아이디별 list 게시글 받아오기
        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }


}
