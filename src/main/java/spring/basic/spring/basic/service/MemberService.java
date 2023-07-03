package spring.basic.spring.basic.service;

import spring.basic.spring.basic.domain.Member;
import spring.basic.spring.basic.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 중복 회원 X

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Optional 이므로 if (!result) => {} 와 같은 문법을 안써도댐
        // 그냥 꺼내고 싶으면 .get() 으로 꺼내면 되는데 권장되지 않음 (동시성?)
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> { //값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 전체 회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원조회
     */
    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }

}
