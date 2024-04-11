package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;

    // MemberService 상의 동일한 이름과는 다른 객체다 (굳이 2개를 쓸 필요가 없다 -> 이왕이면 같은 객체를 쓰는게 테스트상에서 더 유의미하지 않을까?)
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member); // -> 우리가 저장한 멤버가 레포지토리에 있는 것이 맞는가를 테스트하기 위한 것

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 중복 회원을 예외시키는 로직이 잘 작동하는지 테스트
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1  =  new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // memberService.join(member2)를 실행시켰을 때, IllegalStateException이 발생해야 한다는 뜻
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



//        try{
//            memberService.join(member2); //여기서 예외 발생해야 함 ("이미 존재하는 회원입니다.")
//            fail(""); //실패 지점
//        } catch (IllegalStateException e) { // 성공지점
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
