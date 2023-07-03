package spring.basic.spring.basic.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.basic.spring.basic.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; //static import
import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
        TEST 는 서로 순서 , 의존 관계 상관없이 설계 되어야함
        그래서 한 테스트 가 끝날때마다 데이터를 초기화 해줌

     */

    @AfterEach // callback method
    public void afterEach() {
        repository.clearStore();
    }



    @Test
    public  void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // get 으로 바로 꺼내는건 좋은 방법이 아님

        // Assertions.assertThat(member).isEqualTo(result); static import 하면 아래처럼 변환가능 alt + enter
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result =  repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

