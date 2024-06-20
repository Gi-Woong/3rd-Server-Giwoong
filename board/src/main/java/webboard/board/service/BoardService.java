package webboard.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webboard.board.dto.BoardDTO;
import webboard.board.entity.BoardEntity;
import webboard.board.repository.BoardRepository;

// DTO -> Entity (Entity Class)
// Entity -> DTO 이 변환과정을 수행 (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    //레포지토리는 기본적으로 Entity 클래스만 받아준다.
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        // 기본적으로 Entity 타입의 리턴을 주게 되어있음
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
